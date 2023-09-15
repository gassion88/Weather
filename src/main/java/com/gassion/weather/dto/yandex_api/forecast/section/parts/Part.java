package com.gassion.weather.dto.yandex_api.forecast.section.parts;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Part {
    @JsonProperty("temp")
    private int temp;
    @JsonProperty("temp_min")
    private int tempMin;

    @JsonProperty("temp_max")
    private int tempMax;

    @JsonProperty("temp_avg")
    private int tempAvg;

    @JsonProperty("feels_like")
    private int feelsLike;

    @JsonProperty("icon")
    private String icon;

    @JsonProperty("condition")
    private String condition;

    @JsonProperty("daytime")
    private String dayTime;

    @JsonProperty("polar")
    private boolean polar;

    @JsonProperty("wind_speed")
    private double windSpeed;

    @JsonProperty("pressure_mm")
    private int pressureMM;

    @JsonProperty("humidity")
    private int humidity;

    @JsonProperty("cloudness")
    private double cloudness;
}
