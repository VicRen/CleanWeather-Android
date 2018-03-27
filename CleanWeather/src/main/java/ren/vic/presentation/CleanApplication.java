package ren.vic.presentation;

import com.squareup.leakcanary.LeakCanary;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;
import ren.vic.presentation.internal.di.components.DaggerApplicationComponent;

public class CleanApplication extends DaggerApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        initializeLeakDetection();
    }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerApplicationComponent.builder().application(this).build();
    }

    private void initializeLeakDetection() {
        LeakCanary.install(this);
    }
}
