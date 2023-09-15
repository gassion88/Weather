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

    @JsonProperty("coord")
    private Coordinates coordinates;

    @JsonProperty("weather")
    private List<WeatherCondition> weatherCondition;

    @JsonProperty("base")
    private String base;

    @JsonProperty("main")
    private MainDetails mainDetails;

    @JsonProperty("visibility")
    private int visibility;

    @JsonProperty("wind")
    private Wind wind;

    @JsonProperty("clouds")
    private Clouds clouds;

    @JsonProperty("dt")
    private String DT;

    @JsonProperty("sys")
    private Sys sys;

    @JsonProperty("timezone")
    private int timezone;

    @JsonProperty("id")
    private int id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("cod")
    private int cod;

}