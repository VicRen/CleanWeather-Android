package ren.vic.presentation;

import android.widget.EditText;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import ren.vic.presentation.common.BaseActionBarActivity;

public class MainActivity extends BaseActionBarActivity implements MainContract.View {

    @BindView(R2.id.tvTesting)
    TextView mTvTesting;

    @BindView(R2.id.editText)
    EditText mEdtText;

    @Inject
    MainPresenter mPresenter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData() {
        mTvTesting.setText("Hello world");
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
