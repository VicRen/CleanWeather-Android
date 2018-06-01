package ren.vic.domain.interactor;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import io.reactivex.Observable;
import io.reactivex.observers.DisposableObserver;
import ren.vic.domain.entity.LocationData;
import ren.vic.domain.entity.Weather;
import ren.vic.domain.executor.PostExecutionThread;
import ren.vic.domain.executor.ThreadExecutor;
import ren.vic.domain.repository.LocationRepository;
import ren.vic.domain.repository.WeatherRepository;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;

@RunWith(MockitoJUnitRunner.class)
public class GetWeatherByLocationTest {

    private GetWeatherByLocation getWeatherByLocation;
    private LocationData fakeLocationData;
    private Weather fakeWeather;
    private TestDisposableObserver testObserver;

    @Mock
    private ThreadExecutor threadExecutor;

    @Mock
    private PostExecutionThread postExecutionThread;

    @Mock
    private LocationRepository locationRepository;

    @Mock
    private WeatherRepository weatherRepository;

    @Before
    public void setUp() {
        getWeatherByLocation = new GetWeatherByLocation(threadExecutor, postExecutionThread,
                locationRepository, weatherRepository);
        fakeLocationData = new LocationData();
        fakeWeather = new Weather();
        testObserver = new TestDisposableObserver();
    }

    @Test
    public void testBuildGetWeatherByLocationUseCaseHappyCase() {
        given(locationRepository.currentLocation()).willReturn(Observable.just(fakeLocationData));
        getWeatherByLocation.buildUseCaseObservable(null);

        verify(locationRepository).currentLocation();
        verifyNoMoreInteractions(locationRepository);
        verifyZeroInteractions(threadExecutor, postExecutionThread);
    }

    @Test
    public void testGetWeatherByLocationUseCaseExecuteHappyCase() {
        given(locationRepository.currentLocation()).willReturn(Observable.just(fakeLocationData));
        given(weatherRepository.weatherByLocation(fakeLocationData)).willReturn(Observable.just(fakeWeather));
        getWeatherByLocation.buildUseCaseObservable(null).subscribe(testObserver);

        verify(locationRepository).currentLocation();
        verify(weatherRepository).weatherByLocation(fakeLocationData);
        assertThat(testObserver.nextCount).isEqualTo(1);
        assertThat(testObserver.nextValue).isEqualTo(fakeWeather);
        assertThat(testObserver.completeCount).isEqualTo(1);
        assertThat(testObserver.errorCount).isZero();
        verifyNoMoreInteractions(locationRepository, weatherRepository);
        verifyZeroInteractions(threadExecutor, postExecutionThread);
    }

    @Test
    public void testFailForLocationUnavailable() {
        given(locationRepository.currentLocation()).willReturn(Observable.error(new NullPointerException()));
        getWeatherByLocation.buildUseCaseObservable(null).subscribe(testObserver);

        verify(locationRepository).currentLocation();
        assertThat(testObserver.errorCount).isEqualTo(1);
        verifyNoMoreInteractions(locationRepository);
        verifyZeroInteractions(threadExecutor, postExecutionThread, weatherRepository);
    }

    private static class TestDisposableObserver extends DisposableObserver<Weather> {

        private Weather nextValue;
        private int nextCount;
        private int errorCount;
        private int completeCount;

        @Override
        public void onNext(Weather t) {
            nextValue = t;
            nextCount++;
        }

        @Override
        public void onError(Throwable e) {
            errorCount++;
        }

        @Override
        public void onComplete() {
            completeCount++;
        }
    }
}
