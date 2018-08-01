package ren.vic.presentation;

import javax.inject.Inject;

public class MainPresenter implements MainContract.Presenter {

    private MainContract.View mView;

    @Inject
    MainPresenter() {
    }

    @Override
    public void setView(MainContract.View view) {
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
    }

    @Override
    public void enterCity() {
        mView.navigateToEnterCity();
    }
}
