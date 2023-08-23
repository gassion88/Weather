package com.gassion.weather.dto.forecast.section.parts;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class PartForecast {
    @JsonProperty("night")
    private Part nightPart;

    @JsonProperty("morning")
    private Part morningPart;

    @JsonProperty("day")
    private Part dayPart;

    @JsonProperty("evening")
    private Part eveningPart;
}
