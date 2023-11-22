package com.test.weather.controller;

import com.test.weather.dao.WeatherDAO;
import com.test.weather.entity.Coord;
import com.test.weather.entity.WeatherData;
import com.test.weather.service.WeatherService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DemoController {

    private WeatherDAO weatherDAO;
    private WeatherService weatherService;

    public DemoController(WeatherDAO weatherDAO, WeatherService weatherService) {
        this.weatherDAO = weatherDAO;
        this.weatherService = weatherService;
    }
    // localhost:8080/weather?countryCode=gb&cityName=london
    @GetMapping("/weather")
    public WeatherData getWeather(@RequestParam("countryCode") String countryCode, @RequestParam("cityName") String cityName) {
        Coord coord = weatherDAO.getCoord(countryCode,cityName);
        return weatherService.getWeatherForecast(coord.getLat(), coord.getLon());
    }

    @GetMapping("/search")
    public List<String> getSearchByCity(@RequestParam("query") String query){
        return weatherDAO.getQueryByCity(query);
    }
    @GetMapping("/searches")
    public List<String> getSearchByCity(@RequestParam("country") String country, @RequestParam("city") String city){
        return weatherDAO.getQueryByGlobal(country, city);
    }
}
