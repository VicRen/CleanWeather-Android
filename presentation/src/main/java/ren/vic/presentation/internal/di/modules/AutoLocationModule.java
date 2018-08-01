package ren.vic.presentation.internal.di.modules;

import dagger.Binds;
import dagger.Module;
import ren.vic.presentation.autolocation.AutoLocationContract;
import ren.vic.presentation.autolocation.AutoLocationPresenter;
import ren.vic.presentation.internal.di.scope.ActivityScoped;

@Module
abstract class AutoLocationModule {

    @ActivityScoped
    @Binds
    abstract AutoLocationContract.Presenter provideAutoLocationPresenter(AutoLocationPresenter autoLocationPresenter);
}
