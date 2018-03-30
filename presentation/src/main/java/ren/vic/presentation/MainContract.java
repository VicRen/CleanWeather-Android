package ren.vic.presentation;

import ren.vic.presentation.common.BasePresenter;
import ren.vic.presentation.common.BaseView;

public class MainContract {

    interface View extends BaseView {

        String getText();

        void onShowText(String text);
    }

    interface Presenter extends BasePresenter<View> {

        void go();
    }
}
