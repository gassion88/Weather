package com.gassion.weather.dto.forecast;

import com.gassion.weather.dto.forecast.section.ToDayForecastPart;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ToDayForecastDTO {
    private final List<ToDayForecastPart> hourlyForecast = new ArrayList<>();
}
