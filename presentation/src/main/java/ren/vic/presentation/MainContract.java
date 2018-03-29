package ren.vic.presentation;

import ren.vic.presentation.common.BasePresenter;
import ren.vic.presentation.common.BaseView;

public class MainContract {

    interface View extends BaseView {

        void onShowText(String text);
    }

    interface Presetner extends BasePresenter<View> {
    }
}
