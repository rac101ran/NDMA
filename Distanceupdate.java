package com.example.disastermanagementfinal;

public class Distanceupdate {
    double latitude;
    double longitude;
    double latitudevictim;
    double longitudevictim;

    public  Distanceupdate(double lat,double lon,double latvictiom,double lonvictim ) {

        latitude=lat;
        longitude=lon;
        latitudevictim=latvictiom;
        longitudevictim=lonvictim;

    }
    public double distanceCalculate() {

        double earthRadius = 6371.0;

        double dLat = Math.toRadians(latitude-latitudevictim);
        double dLng = Math.toRadians(longitude-longitudevictim);

        double sindLat = Math.sin(dLat / 2);
        double sindLng = Math.sin(dLng / 2);

        double a = Math.pow(sindLat, 2) + Math.pow(sindLng, 2)
                * Math.cos(Math.toRadians(latitude)) * Math.cos(Math.toRadians(latitudevictim));

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));

        double distance = earthRadius * c;


        return distance;
    }

}

