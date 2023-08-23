package com.gassion.weather.dto.forecast.section;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.gassion.weather.dto.forecast.section.parts.PartForecast;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Forecast {
    @JsonProperty("date")
    private Date dateTime;

    @JsonProperty("sunrise")
    private Date rise;

    @JsonProperty("sunset")
    private Date sunset;

    @JsonProperty("moon_code")
    private String moonPhase;

    @JsonProperty("parts")
    private PartForecast partForecast;

    @JsonProperty("tempAvg")
    private double tempAvg;

    @JsonProperty("iconCode")
    private String iconCode;

    @JsonProperty("condition")
    private String condition;

    @JsonProperty("wind_speed")
    private int windSpeed;

    @JsonProperty("pressure_mm")
    private double pressureMM;

    @JsonProperty("humidity")
    private double humidity;

    @JsonProperty("prec_prob")
    private int precProb;

    @JsonProperty("prec_type")
    private int precType;

    @JsonProperty("cloudness")
    private int cloudness;

    @JsonProperty("hour")
    private String hour;

}
