package ren.vic.presentation.autolocation;

import android.widget.TextView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import ren.vic.presentation.R;
import ren.vic.presentation.R2;
import ren.vic.presentation.common.BaseActionBarActivity;

public class AutoLocationActivity extends BaseActionBarActivity implements AutoLocationContract.View {

    @BindView(R2.id.tvTesting)
    TextView textView;

    @Inject
    AutoLocationContract.Presenter mPresenter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_auto_location;
    }

    @Override
    protected void initData() {
        mPresenter.setView(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.destroy();
    }

    @OnClick(R2.id.button_start_auto_location)
    public void onStartAutoLocation() {
        mPresenter.startAutoLocation();
    }

    @Override
    public void onShowError(String error) {
        textView.setText(error);
    }

    @Override
    public void onShowWeather(String location, String weather) {
        textView.setText(String.format(getString(R.string.weather_at_location), location, weather));
    }
}
