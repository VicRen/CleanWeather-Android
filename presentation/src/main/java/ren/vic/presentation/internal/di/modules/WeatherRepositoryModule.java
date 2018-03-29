package ren.vic.presentation.internal.di.modules;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import ren.vic.data.repository.WeatherDataRepository;
import ren.vic.domain.repository.WeatherRepository;

@Module
public abstract class WeatherRepositoryModule {

    @Singleton
    @Binds
    abstract WeatherRepository provideWeatherRepository(WeatherDataRepository weatherRepository);
}
