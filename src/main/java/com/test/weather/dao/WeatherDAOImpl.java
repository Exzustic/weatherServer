package com.test.weather.dao;

import com.test.weather.entity.Coord;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class WeatherDAOImpl implements WeatherDAO {

    private final JdbcTemplate jdbcTemplate;

    public WeatherDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Coord getCoord(String city) {
        String query = "SELECT Latitude, Longitude FROM Places WHERE City = ? ORDER BY Population";
        List<Coord> coords = jdbcTemplate.query(query, new Object[]{city}, (resultSet, i) -> {
            double latitude = resultSet.getDouble("Latitude");
            double longitude = resultSet.getDouble("Longitude");
            return new Coord(latitude, longitude);
        });

        // Возвращаем первый элемент списка, если он есть
        return coords.isEmpty() ? null : coords.get(0);
    }

    @Override
    public Coord getCoord(String country, String city) {
        String query = "SELECT Latitude, Longitude FROM Places WHERE City = ? and Country = ? ORDER BY Population";
        List<Coord> coords = jdbcTemplate.query(query, new Object[]{city,country}, (resultSet, i) -> {
            double latitude = resultSet.getDouble("Latitude");
            double longitude = resultSet.getDouble("Longitude");
            return new Coord(latitude, longitude);
        });

        // Возвращаем первый элемент списка, если он есть
        return coords.isEmpty() ? null : coords.get(0);
    }

    @Override
    public List<String> getQueryByCity(String city) {
        String query = "SELECT Country, City FROM Places WHERE City like ? GROUP BY Country, City ORDER BY City LIMIT 5";
        List<String> list = jdbcTemplate.query(query, new Object[]{city + "%"}, (resultSet, rowNum) ->
                resultSet.getString("Country") + "," + resultSet.getString("City"));
        return list.isEmpty() ? null : list;
    }

    @Override
    public List<String> getQueryByGlobal(String country, String city) {
        String query = "SELECT Country, City FROM Places WHERE Country = ? and City like ? GROUP BY Country, City ORDER BY City LIMIT 5";
        List<String> list = jdbcTemplate.query(query, new Object[]{country,city + "%"}, (resultSet, rowNum) ->
                resultSet.getString("Country") + ", " + resultSet.getString("City"));
        return list.isEmpty() ? null : list;
    }
}
