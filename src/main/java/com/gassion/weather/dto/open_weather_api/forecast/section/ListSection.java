package com.gassion.weather.dto.open_weather_api.forecast.section;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ListSection {

    @JsonProperty("dt")
    private long dt;

    @JsonProperty("main")
    private MainDetails mainDetails;

    @JsonProperty("weather")
    private List<WeatherCondition> weatherCondition;

    @JsonProperty("clouds")
    private Clouds clouds;

    @JsonProperty("wind")
    private Wind wind;

    @JsonProperty("visibility")
    private int visibility;

    @JsonProperty("pop")
    private double pop;

    @JsonProperty("rain")
    private Rain rain;

    @JsonProperty("sys")
    private Sys sys;

    @JsonProperty("dt_txt")
    private String dt_txt;

}
