package ren.vic.presentation.entercity;

import android.widget.EditText;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import ren.vic.presentation.R;
import ren.vic.presentation.R2;
import ren.vic.presentation.common.BaseActionBarActivity;

public class EnterCityActivity extends BaseActionBarActivity implements EnterCityContract.View {

    @BindView(R2.id.tvTesting)
    TextView mTvTesting;

    @BindView(R2.id.editText)
    EditText mEdtText;

    @Inject
    EnterCityContract.Presenter mPresenter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_enter_city;
    }

    @Override
    protected void initData() {
        mPresenter.setView(this);
    }

    @Override
    public String getText() {
        return mEdtText.getText().toString().trim();
    }

    @Override
    public void onShowText(String text) {
        mTvTesting.setText(text);
    }

    @OnClick(R2.id.button)
    public void onGo() {
        mPresenter.go();
    }
}
