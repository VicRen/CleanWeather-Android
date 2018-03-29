package ren.vic.data.web;

import java.util.Map;

import io.reactivex.Observable;
import ren.vic.data.entity.WeatherEntity;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface WeatherApi {

    @GET("now")
    Observable<WeatherEntity> getCurrentWeather(@QueryMap Map<String, String> queryMap);
}
