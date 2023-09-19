package com.gassion.weather.dto.open_weather_api.forecast;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.gassion.weather.dto.ForecastApiResponseDTO;
import com.gassion.weather.dto.open_weather_api.forecast.section.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class OpenWeatherForecastApiResponseDTO extends ForecastApiResponseDTO {

    @JsonProperty("cod")
    private String cod;

    @JsonProperty("message")
    private int message;

    @JsonProperty("cnt")
    private int cnt;

    @JsonProperty("list")
    private List<ListSection> list;

    @JsonProperty("city")
    private CitySection city;

}