package ren.vic.data.repository;

import io.reactivex.Observable;
import ren.vic.domain.entity.Weather;
import ren.vic.domain.repository.WeatherRepository;

public class WeatherDataRepository implements WeatherRepository {

    @Override
    public Observable<Weather> weatherByCityName(String cityName) {
        return null;
    }
}
