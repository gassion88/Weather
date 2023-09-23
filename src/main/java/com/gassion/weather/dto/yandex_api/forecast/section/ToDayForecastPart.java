package com.gassion.weather.dto.yandex_api.forecast.section;

import lombok.Data;

@Data
public class ToDayForecastPart {

    private final String time;

    private final String condition;

    private final String temp;

    private final String icon;

}
