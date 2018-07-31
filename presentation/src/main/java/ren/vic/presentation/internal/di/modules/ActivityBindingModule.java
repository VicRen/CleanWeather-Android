package ren.vic.presentation.internal.di.modules;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import ren.vic.presentation.MainActivity;
import ren.vic.presentation.internal.di.scope.ActivityScoped;

@Module
public abstract class ActivityBindingModule {

    @ActivityScoped
    @ContributesAndroidInjector
    abstract MainActivity mainActivity();
}
