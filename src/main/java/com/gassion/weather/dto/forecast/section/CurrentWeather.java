package com.gassion.weather.dto.forecast.section;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class CurrentWeather {
    @JsonProperty("temp")
    private double temp;

    @JsonProperty("feels_like")
    private double feelsLike;

    @JsonProperty("name")
    private String icon;

    @JsonProperty("condition")
    private String condition;

    @JsonProperty("wind_speed")
    private int windSpeed;

    @JsonProperty("pressure_mm")
    private double pressureMM;

    @JsonProperty("humidity")
    private double humidity;

    @JsonProperty("daytime")
    private String dayTime;

    @JsonProperty("season")
    private String season;

    @JsonProperty("is_thunder")
    private boolean isThunder;

    @JsonProperty("prec_type")
    private int precType;

    @JsonProperty("cloudness")
    private int cloudness;
}
