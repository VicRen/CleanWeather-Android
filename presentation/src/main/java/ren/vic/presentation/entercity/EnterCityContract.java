package ren.vic.presentation.entercity;

import ren.vic.presentation.common.BasePresenter;
import ren.vic.presentation.common.BaseView;

public class EnterCityContract {

    public interface View extends BaseView {

        String getText();

        void onShowWeather(String location, String weather);

        void onShowError(String error);
    }

    public interface Presenter extends BasePresenter<View> {

        void go();
    }
}
