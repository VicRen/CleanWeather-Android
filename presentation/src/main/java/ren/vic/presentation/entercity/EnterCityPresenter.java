package ren.vic.presentation.entercity;

import javax.inject.Inject;

import ren.vic.domain.entity.Weather;
import ren.vic.domain.interactor.DefaultObserver;
import ren.vic.domain.interactor.WeatherGetToday;

public class EnterCityPresenter implements EnterCityContract.Presenter {

    private final WeatherGetToday weatherGetToday;
    private EnterCityContract.View mView;

    @Inject
    EnterCityPresenter(WeatherGetToday weatherGetToday) {
        this.weatherGetToday = weatherGetToday;
    }

    @Override
    public void setView(EnterCityContract.View view) {
        mView = view;
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {
        weatherGetToday.dispose();
    }

    @Override
    public void go() {
        weatherGetToday.execute(new WeatherObserver(), WeatherGetToday.Param.forCityName(mView.getText()));
    }

    private final class WeatherObserver extends DefaultObserver<Weather> {

        @Override
        public void onNext(Weather weather) {
            mView.onShowWeather(weather.location, weather.condTxt);
        }

        @Override
        public void onError(Throwable e) {
            mView.onShowError("Error");
        }
    }
}
