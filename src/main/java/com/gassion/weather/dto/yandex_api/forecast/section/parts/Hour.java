package com.gassion.weather.dto.yandex_api.forecast.section.parts;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Hour {
    @JsonProperty("hour")
    private String hour;

    @JsonProperty("temp")
    private int temp;

    @JsonProperty("icon")
    private String icon;

    @JsonProperty("condition")
    private String condition;

    @JsonProperty("wind_speed")
    private double windSpeed;

    @JsonProperty("pressure_mm")
    private int pressureMM;

    @JsonProperty("humidity")
    private int humidity;

    @JsonProperty("is_thunder")
    private boolean isThunder;

    @JsonProperty("cloudness")
    private double cloudness;
}
