package ren.vic.data.repository;

import android.content.Context;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Observable;
import ren.vic.data.web.RetrofitInstance;
import ren.vic.data.web.WeatherApi;
import ren.vic.domain.entity.Weather;
import ren.vic.domain.repository.WeatherRepository;

public class WeatherDataRepository implements WeatherRepository {

    private final Context context;
    private final WeatherApi weatherApi;

    @Inject
    WeatherDataRepository(Context context, RetrofitInstance retrofitInstance) {
        this.context = context;
        this.weatherApi = retrofitInstance.getWeatherApi();
    }

    @Override
    public Observable<Weather> weatherByCityName(String cityName) {
        Map<String, String> map = new HashMap<>();
        map.put("sign", "kkFss8dsyjjNBhHqKMTw8Q==");
        map.put("username", "HE1712160911301054");
        map.put("location", "ningbo");
        map.put("t", "1515815034");
        return weatherApi.getCurrentWeather(map)
                .flatMap(weatherEntity -> Observable.create(e -> {
                    Weather weather = new Weather();
                    weather.condTxt = weatherEntity.getWeatherNow().condTxt;
                    e.onNext(weather);
                    e.onComplete();
                }));
    }
}
