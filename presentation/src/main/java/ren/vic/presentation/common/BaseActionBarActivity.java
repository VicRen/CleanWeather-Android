package ren.vic.presentation.common;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import javax.inject.Inject;

import butterknife.ButterKnife;
import dagger.android.support.DaggerAppCompatActivity;
import ren.vic.presentation.R;
import ren.vic.presentation.navigation.Navigator;

public abstract class BaseActionBarActivity extends DaggerAppCompatActivity {

    protected final String TAG = this.getClass().getSimpleName();

    protected ActionBar mAppBar;

    @Inject
    protected Navigator mNavigator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        log("--->onCreate");
        initialize();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        log("--->onDestroy");
    }

    @Override
    protected void onStart() {
        super.onStart();
        log("--->onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        log("--->onStop");
    }

    @Override
    protected void onResume() {
        super.onResume();
        log("--->onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        log("--->onPause");
    }

    protected void initialize() {
        setContentView(getLayoutId());
        initView();
        setupAppBar();
        initData();
    }

    protected void setupAppBar() {
        Toolbar toolBar = findViewById(R.id.toolbar);
        if (toolBar != null) {
            setSupportActionBar(toolBar);
        }
        mAppBar = getSupportActionBar();
    }

    /**
     * 获得页面布局Id
     *
     * @return 布局Id
     */
    protected abstract int getLayoutId();

    /**
     * 初始化控件
     */
    protected void initView() {
        ButterKnife.bind(this);
    }

    /**
     * 初始化数据
     */
    protected abstract void initData();

    protected void log(String msg) {
        Log.i(TAG, msg);
    }
}
