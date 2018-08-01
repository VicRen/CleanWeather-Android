package ren.vic.presentation;

import ren.vic.presentation.common.BasePresenter;
import ren.vic.presentation.common.BaseView;

public class MainContract {

    public interface View extends BaseView {

        void navigateToEnterCity();

        void navigateToAutoLocation();
    }

    public interface Presenter extends BasePresenter<View> {

        void enterCity();

        void autoLocation();
    }
}
