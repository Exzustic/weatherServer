package com.test.weather.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class WeatherData {
    @SerializedName("weather")
    private Weather[] weather;

    @SerializedName("main")
    private Main main;

    @SerializedName("wind")
    private Wind wind;
}



@Data
@JsonIgnoreProperties(ignoreUnknown = true)
class Weather {
    private String main;
    private String description;
}

@Data
class Main {
    private double temp;
    private double feels_like;
    private double temp_min;
    private double temp_max;
}

@Data
class Wind {
    private double speed;
    private int deg;
}

