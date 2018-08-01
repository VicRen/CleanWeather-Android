package ren.vic.presentation;

import javax.inject.Inject;

import butterknife.OnClick;
import ren.vic.presentation.common.BaseActionBarActivity;

public class MainActivity extends BaseActionBarActivity implements MainContract.View {

    @Inject
    MainContract.Presenter mPresenter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData() {
        mPresenter.setView(this);
    }

    @Override
    public void navigateToEnterCity() {
        mNavigator.navigateToEnterCity(this);
    }

    @OnClick(R2.id.button_enter_city)
    public void onEnterCity() {
        mPresenter.enterCity();
    }
}
