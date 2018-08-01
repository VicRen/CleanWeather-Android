package ren.vic.presentation.internal.di.modules;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import ren.vic.presentation.MainActivity;
import ren.vic.presentation.entercity.EnterCityActivity;
import ren.vic.presentation.internal.di.scope.ActivityScoped;

@Module
public abstract class ActivityBindingModule {

    @ActivityScoped
    @ContributesAndroidInjector(modules = MainActivityModule.class)
    abstract MainActivity mainActivity();

    @ActivityScoped
    @ContributesAndroidInjector(modules = EnterCityActivityModule.class)
    abstract EnterCityActivity enterCityActivity();
}
