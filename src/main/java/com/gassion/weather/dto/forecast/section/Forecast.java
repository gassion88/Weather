package com.gassion.weather.dto.forecast.section;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.gassion.weather.dto.forecast.section.parts.Hour;
import com.gassion.weather.dto.forecast.section.parts.PartForecast;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Forecast {
    @JsonProperty("date")
    private String dateTime;

    @JsonProperty("sunrise")
    private String rise;

    @JsonProperty("sunset")
    private String sunset;

    @JsonProperty("moon_code")
    private int moonPhase;

    @JsonProperty("parts")
    private PartForecast partForecast;

    @JsonProperty("hours")
    private List<Hour> hours;
}
