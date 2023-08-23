package com.gassion.weather.dto.forecast;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.gassion.weather.dto.forecast.section.CurrentWeather;
import com.gassion.weather.dto.forecast.section.Forecast;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ForecastApiResponse {
    @JsonProperty("fact")
    private CurrentWeather currentWeather;

    @JsonProperty("forecasts")
    private Forecast forecast;
}
