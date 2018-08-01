package ren.vic.presentation.internal.di.modules;

import dagger.Binds;
import dagger.Module;
import ren.vic.presentation.entercity.EnterCityContract;
import ren.vic.presentation.entercity.EnterCityPresenter;
import ren.vic.presentation.internal.di.scope.ActivityScoped;

@Module
public abstract class EnterCityActivityModule {

    @ActivityScoped
    @Binds
    abstract EnterCityContract.Presenter provideEnterCityPresenter(EnterCityPresenter enterCityPresenter);
}
