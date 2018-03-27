package ren.vic.domain.interactor;

import com.fernandocejas.arrow.checks.Preconditions;

import javax.inject.Inject;

import io.reactivex.Observable;
import ren.vic.domain.entity.City;
import ren.vic.domain.executor.PostExecutionThread;
import ren.vic.domain.executor.ThreadExecutor;
import ren.vic.domain.repository.CityRepository;

public class CityGetOne extends UseCase<City, CityGetOne.Param> {

    private final CityRepository cityRepository;

    @Inject
    CityGetOne(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread,
               CityRepository cityRepository) {
        super(threadExecutor, postExecutionThread);
        this.cityRepository = cityRepository;
    }

    @Override
    Observable<City> buildUseCaseObservable(Param param) {
        Preconditions.checkNotNull(param);
        return cityRepository.city(param.cityId);
    }

    public static final class Param {

        private final int cityId;
        private Param(int cityId) {
            this.cityId = cityId;
        }

        public static Param forCity(int cityId) {
            return new Param(cityId);
        }
    }
}
