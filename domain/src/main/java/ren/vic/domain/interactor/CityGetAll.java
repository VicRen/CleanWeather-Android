package ren.vic.domain.interactor;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import ren.vic.domain.entity.City;
import ren.vic.domain.executor.PostExecutionThread;
import ren.vic.domain.executor.ThreadExecutor;
import ren.vic.domain.repository.CityRepository;

public class CityGetAll extends UseCase<List<City>, Void> {

    private final CityRepository cityRepository;

    @Inject
    CityGetAll(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread,
               CityRepository cityRepository) {
        super(threadExecutor, postExecutionThread);
        this.cityRepository = cityRepository;
    }

    @Override
    Observable<List<City>> buildUseCaseObservable(Void aVoid) {
        return cityRepository.cityList();
    }
}
