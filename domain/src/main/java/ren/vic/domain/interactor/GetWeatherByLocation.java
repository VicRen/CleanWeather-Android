package ren.vic.domain.interactor;

import javax.inject.Inject;

import io.reactivex.Observable;
import ren.vic.domain.entity.Weather;
import ren.vic.domain.executor.PostExecutionThread;
import ren.vic.domain.executor.ThreadExecutor;
import ren.vic.domain.repository.LocationRepository;
import ren.vic.domain.repository.WeatherRepository;

public class GetWeatherByLocation extends UseCase<Weather, Void> {

    private final LocationRepository locationRepository;
    private final WeatherRepository weatherRepository;

    @Inject
    GetWeatherByLocation(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread,
                         LocationRepository locationRepository, WeatherRepository weatherRepository) {
        super(threadExecutor, postExecutionThread);
        this.locationRepository = locationRepository;
        this.weatherRepository = weatherRepository;
    }

    @Override
    Observable<Weather> buildUseCaseObservable(Void aVoid) {
        return locationRepository.currentLocation()
                .flatMap(weatherRepository::weatherByLocation);
    }
}
