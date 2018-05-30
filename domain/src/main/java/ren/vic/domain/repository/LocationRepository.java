package ren.vic.domain.repository;

import io.reactivex.Observable;
import ren.vic.domain.entity.LocationData;

public interface LocationRepository {

    Observable<LocationData> currentLocation();
}
