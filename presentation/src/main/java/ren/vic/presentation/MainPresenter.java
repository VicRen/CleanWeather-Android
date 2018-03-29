package ren.vic.presentation;

import javax.inject.Inject;

import ren.vic.domain.entity.Weather;
import ren.vic.domain.interactor.DefaultObserver;
import ren.vic.domain.interactor.WeatherGetToday;

public class MainPresenter implements MainContract.Presetner {

    private final WeatherGetToday mWeatherGetToday;
    private MainContract.View mView;

    @Inject
    MainPresenter(WeatherGetToday weatherGetToday) {
        mWeatherGetToday = weatherGetToday;
    }

    @Override
    public void setView(MainContract.View view) {
        mView = view;
        mWeatherGetToday.execute(new WeatherObserver(), WeatherGetToday.Param.forCityName("ningbo"));
    }

    @Override
    public void resume() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void destroy() {
    }

    private final class WeatherObserver extends DefaultObserver<Weather> {

        @Override
        public void onNext(Weather weather) {
            mView.onShowText(weather.condTxt);
        }

        @Override
        public void onError(Throwable e) {
            mView.onShowText("Error");
        }
    }
}
