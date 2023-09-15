package com.gassion.weather.dto.open_weather_api.forecast.section;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class MainDetails {

    @JsonProperty("temp")
    private double temp;

    @JsonProperty("feels_like")
    private double feels_like;

    @JsonProperty("temp_min")
    private double temp_min;

    @JsonProperty("temp_max")
    private double temp_max;

    @JsonProperty("pressure")
    private int pressure;

    @JsonProperty("humidity")
    private int humidity;

}
