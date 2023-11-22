package com.test.weather.entity;

import lombok.Data;

@Data
public class Coord {
    private double lon;
    private double lat;

    public Coord() {
    }

    public Coord(double lat, double lon) {
        this.lon = lon;
        this.lat = lat;
    }
}
