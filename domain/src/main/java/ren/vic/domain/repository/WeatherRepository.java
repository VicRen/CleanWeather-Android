package ren.vic.domain.repository;

import io.reactivex.Observable;
import ren.vic.domain.entity.LocationData;
import ren.vic.domain.entity.Weather;

public interface WeatherRepository {

    Observable<Weather> weatherByCityName(String cityName);

    Observable<Weather> weatherByLocation(LocationData fakeLocationData);
}
