package ren.vic.domain.repository;

import io.reactivex.Observable;
import ren.vic.domain.entity.Weather;

public interface WeatherRepository {

    public Observable<Weather> weatherByCityName(String cityName);
}
