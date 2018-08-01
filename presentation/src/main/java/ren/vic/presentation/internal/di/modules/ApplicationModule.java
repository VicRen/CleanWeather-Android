package ren.vic.presentation.internal.di.modules;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import ren.vic.data.executor.JobExecutor;
import ren.vic.data.repository.LocationDataRepository;
import ren.vic.data.repository.WeatherDataRepository;
import ren.vic.domain.executor.PostExecutionThread;
import ren.vic.domain.executor.ThreadExecutor;
import ren.vic.domain.repository.LocationRepository;
import ren.vic.domain.repository.WeatherRepository;
import ren.vic.presentation.threading.UIThread;

@Module
public abstract class ApplicationModule {

    @Singleton
    @Binds
    abstract Context provideApplicationContext(Application application);

    @Singleton
    @Binds
    abstract ThreadExecutor provideThreadExecutor(JobExecutor jobExecutor);

    @Singleton
    @Binds
    abstract PostExecutionThread providePostExecutionThread(UIThread uiThread);

    @Singleton
    @Binds
    abstract WeatherRepository provideWeatherRepository(WeatherDataRepository weatherRepository);

    @Singleton
    @Binds
    abstract LocationRepository provideLocationRepository(LocationDataRepository locationRepository);
}
