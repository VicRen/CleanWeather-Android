package ren.vic.presentation.autolocation;

import ren.vic.presentation.common.BasePresenter;
import ren.vic.presentation.common.BaseView;

public class AutoLocationContract {

    public interface View extends BaseView {

        void onShowError(String error);

        void onShowWeather(String location, String weather);
    }

    public interface Presenter extends BasePresenter<View> {

        void startAutoLocation();
    }
}
