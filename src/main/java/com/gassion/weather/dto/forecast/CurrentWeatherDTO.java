package com.gassion.weather.dto.forecast;

import lombok.Data;

@Data
public class CurrentWeatherDTO {
    private final String locationName;
    private final String condition;
    private final double temp;
    private final double feelsLike;
    private final double pressureMM;
    private final int windSpeed;
    private final double humidity;
}
