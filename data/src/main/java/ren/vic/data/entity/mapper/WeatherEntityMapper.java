package ren.vic.data.entity.mapper;

import javax.inject.Inject;
import javax.inject.Singleton;

import ren.vic.data.entity.WeatherEntity;
import ren.vic.domain.entity.Weather;

@Singleton
public class WeatherEntityMapper {

    @Inject
    WeatherEntityMapper() {
    }

    public Weather transform(WeatherEntity weatherEntity) {
        Weather weather = null;
        if (weatherEntity != null) {
            weather = new Weather();
            weather.condTxt = weatherEntity.getWeatherNow().condTxt;
        }
        return weather;
    }
}
