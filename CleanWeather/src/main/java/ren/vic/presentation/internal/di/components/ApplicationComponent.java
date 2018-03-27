package ren.vic.presentation.internal.di.components;

import android.app.Application;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;
import dagger.android.support.AndroidSupportInjectionModule;
import ren.vic.presentation.internal.di.modules.ApplicationModule;
import ren.vic.presentation.internal.di.modules.BindingActivityModule;

@Singleton
@Component(modules = {
        ApplicationModule.class,
        BindingActivityModule.class,
        AndroidSupportInjectionModule.class
})
public interface ApplicationComponent extends AndroidInjector<DaggerApplication> {

    @Override
    void inject(DaggerApplication instance);

    @Component.Builder
    interface Builder {

        @BindsInstance
        ApplicationComponent.Builder application(Application application);

        ApplicationComponent build();
    }
}
