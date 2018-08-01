package ren.vic.domain.entity;

public class LocationData {

    private double longitude;
    private double latitude;

    public LocationData(double longitude, double latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }
}
