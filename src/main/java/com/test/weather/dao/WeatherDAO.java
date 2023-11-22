package com.test.weather.dao;


import com.test.weather.entity.Coord;

import java.util.List;

public interface WeatherDAO {

    Coord getCoord(String city);

    Coord getCoord(String country, String city);

    List<String> getQueryByCity(String city);

    List<String> getQueryByGlobal(String country, String city);
}
