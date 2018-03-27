package ren.vic.domain.repository;

import java.util.List;

import io.reactivex.Observable;
import ren.vic.domain.entity.City;

public interface CityRepository {

    Observable<List<City>> cityList();

    Observable<City> city(int cityId);
}
