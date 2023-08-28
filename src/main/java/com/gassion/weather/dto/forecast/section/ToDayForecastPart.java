package com.gassion.weather.dto.forecast.section;

import lombok.Data;

@Data
public class ToDayForecastPart {
    private final String time;
    private final String condition;
    private final String temp;
}
