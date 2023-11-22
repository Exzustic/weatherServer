package com.test.weather.service;

import com.test.weather.entity.WeatherData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {

    @Value("${weather.url}")
    private String API_URL;

    @Value("${weather.api}")
    private String API_KEY;

    private RestTemplate restTemplate;

    public WeatherService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public WeatherData getWeatherForecast(double lat, double lon) {
        String url = String.format("%s?lat=%f&lon=%f&&appid=%s", API_URL, lat, lon, API_KEY);
        return restTemplate.getForObject(url, WeatherData.class);
    }
}