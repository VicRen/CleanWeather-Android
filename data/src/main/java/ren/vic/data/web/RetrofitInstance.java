package ren.vic.data.web;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Singleton
public class RetrofitInstance {

    private static final String PRODUCTION_BASE_URL = "https://free-api.heweather.com/s6/weather/";

    private final Retrofit retrofit;
    private WeatherApi weatherApi;

    @Inject
    RetrofitInstance() {
        this.retrofit = new Retrofit.Builder()
                .baseUrl(PRODUCTION_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    public WeatherApi getWeatherApi() {
        if (weatherApi == null) {
            weatherApi = retrofit.create(WeatherApi.class);
        }
        return weatherApi;
    }
}
