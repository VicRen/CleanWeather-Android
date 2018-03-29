package ren.vic.domain.interactor;

import com.fernandocejas.arrow.checks.Preconditions;

import javax.inject.Inject;

import io.reactivex.Observable;
import ren.vic.domain.entity.Weather;
import ren.vic.domain.executor.PostExecutionThread;
import ren.vic.domain.executor.ThreadExecutor;
import ren.vic.domain.repository.WeatherRepository;

public class WeatherGetToday extends UseCase<Weather, WeatherGetToday.Param> {

    private final WeatherRepository weatherRepository;

    @Inject
    WeatherGetToday(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread,
                    WeatherRepository weatherRepository) {
        super(threadExecutor, postExecutionThread);
        this.weatherRepository = weatherRepository;
    }

    @Override
    Observable<Weather> buildUseCaseObservable(Param param) {
        Preconditions.checkNotNull(param);
        return weatherRepository.weatherByCityName(param.cityName);
    }

    public static final class Param {

        private String cityName;

        private Param(String cityName) {
            this.cityName = cityName;
        }

        public static Param forCityName(String cityName) {
            return new Param(cityName);
        }
    }
}
