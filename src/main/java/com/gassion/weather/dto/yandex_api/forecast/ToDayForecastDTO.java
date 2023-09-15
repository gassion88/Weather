package com.gassion.weather.dto.yandex_api.forecast;

import com.gassion.weather.dto.yandex_api.forecast.section.ToDayForecastPart;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ToDayForecastDTO {
    private final List<ToDayForecastPart> hourlyForecast = new ArrayList<>();
}
