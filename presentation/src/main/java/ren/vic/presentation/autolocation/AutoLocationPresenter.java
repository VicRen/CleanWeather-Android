package ren.vic.presentation.autolocation;

import javax.inject.Inject;

import ren.vic.domain.entity.Weather;
import ren.vic.domain.interactor.DefaultObserver;
import ren.vic.domain.interactor.GetWeatherByLocation;

public class AutoLocationPresenter implements AutoLocationContract.Presenter {

    private final GetWeatherByLocation getWeatherByLocation;

    private AutoLocationContract.View view;

    @Inject
    AutoLocationPresenter(GetWeatherByLocation getWeatherByLocation) {
        this.getWeatherByLocation = getWeatherByLocation;
    }

    @Override
    public void setView(AutoLocationContract.View view) {
        this.view = view;
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {
        getWeatherByLocation.dispose();
    }

    @Override
    public void startAutoLocation() {
        getWeatherByLocation.execute(new WeatherObserver(), null);
    }

    private final class WeatherObserver extends DefaultObserver<Weather> {

        @Override
        public void onNext(Weather weather) {
            view.onShowWeather(weather.location, weather.condTxt);
        }

        @Override
        public void onError(Throwable e) {
            view.onShowError("Error");
        }
    }
}
