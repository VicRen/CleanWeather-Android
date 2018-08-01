package ren.vic.presentation.internal.di.modules;

import dagger.Binds;
import dagger.Module;
import ren.vic.presentation.MainContract;
import ren.vic.presentation.MainPresenter;
import ren.vic.presentation.internal.di.scope.ActivityScoped;

@Module
public abstract class MainActivityModule {

    @ActivityScoped
    @Binds
    abstract MainContract.Presenter provideMainPresenter(MainPresenter mainPresenter);
}
