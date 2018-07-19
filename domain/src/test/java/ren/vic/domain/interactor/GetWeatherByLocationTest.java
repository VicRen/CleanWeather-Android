package ren.vic.domain.interactor;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;
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
    private TestObserver<Weather> testObserver;

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
        testObserver = TestObserver.create();
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

        assertThat(testObserver.valueCount()).isEqualTo(1);
        assertThat(testObserver.values().get(0)).isEqualTo(fakeWeather);
        assertThat(testObserver.assertComplete());
        assertThat(testObserver.assertNoErrors());

        verify(locationRepository).currentLocation();
        verify(weatherRepository).weatherByLocation(fakeLocationData);
        verifyNoMoreInteractions(locationRepository, weatherRepository);
        verifyZeroInteractions(threadExecutor, postExecutionThread);
    }

    @Test
    public void testFailForLocationUnavailable() {
        given(locationRepository.currentLocation()).willReturn(Observable.error(new NullPointerException()));

        getWeatherByLocation.buildUseCaseObservable(null).subscribe(testObserver);

        verify(locationRepository).currentLocation();
//        assertThat(testObserver.errorCount).isEqualTo(1);
        verifyNoMoreInteractions(locationRepository);
        verifyZeroInteractions(threadExecutor, postExecutionThread, weatherRepository);
    }
}
