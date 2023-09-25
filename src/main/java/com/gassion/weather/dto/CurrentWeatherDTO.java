package com.gassion.weather.dto;

import lombok.Data;

@Data
public class CurrentWeatherDTO {
    private final String locationName;
    private final String condition;
    private final String temp;
    private final double feelsLike;
    private final double pressureMM;
    private final double windSpeed;
    private final double humidity;
}
