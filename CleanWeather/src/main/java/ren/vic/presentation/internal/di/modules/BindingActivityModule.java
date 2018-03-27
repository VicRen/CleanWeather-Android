package ren.vic.presentation.internal.di.modules;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import ren.vic.cleanweather.home.HomeActivity;
import ren.vic.presentation.MainActivity;
import ren.vic.presentation.internal.di.scope.ActivityScoped;

@Module
public abstract class BindingActivityModule {

    @ActivityScoped
    @ContributesAndroidInjector(modules = BlankActivityModule.class)
    abstract MainActivity mainActivity();

    @ActivityScoped
    @ContributesAndroidInjector(modules = BlankActivityModule.class)
    abstract HomeActivity homeActivity();

}
