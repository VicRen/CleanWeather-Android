package ren.vic.data.repository;

import android.content.Context;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import ren.vic.domain.entity.LocationData;
import ren.vic.domain.repository.LocationRepository;

@Singleton
public class LocationDataRepository implements LocationRepository {

    private final Context context;

    @Inject
    LocationDataRepository(Context context) {
        this.context = context;
    }

    @Override
    public Observable<LocationData> currentLocation() {
        return Observable.just(new LocationData( 121.4737000000, 31.2303700000));
    }
}
