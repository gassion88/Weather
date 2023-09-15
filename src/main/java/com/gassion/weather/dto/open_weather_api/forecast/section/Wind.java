package com.gassion.weather.dto.open_weather_api.forecast.section;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Wind {

    @JsonProperty("speed")
    private double speed;

    @JsonProperty("deg")
    private int deg;

}
