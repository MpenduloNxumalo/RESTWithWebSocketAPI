package com.example.bts.RESTWithWebSocketAPI.models;

public class GeolocationModel {
    double latitude = 0;
    double longitude = 0;
    String timeStamp = null;

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    @Override
    public String toString() {
        return "GeolocationModel{" +
                "latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                ", timeStamp='" + timeStamp + '\'' +
                '}';
    }
}