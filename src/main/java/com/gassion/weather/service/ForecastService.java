package com.gassion.weather.service;

import com.gassion.weather.dto.ForecastApiResponseDTO;
import com.gassion.weather.dto.CurrentWeatherDTO;
import com.gassion.weather.dto.ToDayForecastDTO;
import com.gassion.weather.dto.yandex_api.forecast.section.ToDayForecastPart;

import java.text.DecimalFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public interface ForecastService {

    ForecastApiResponseDTO loadForecastByCoordinates(String lot, String lon);

    CurrentWeatherDTO getCurrentWeather(ForecastApiResponseDTO yandexApiForecastApiResponseDTO, String locationName);

    ToDayForecastDTO getToDayForecast(ForecastApiResponseDTO yandexApiForecastApiResponseDTO);

    default int getCurrentHourFromZone(String zoneName) {
        Instant instant = Instant.now();
        ZoneId z = ZoneId.of(zoneName);
        ZonedDateTime zdt = instant.atZone(z);
        return zdt.getHour();
    }

    default void addForecastToDTO(ToDayForecastDTO toDayForecastDTO, String hourInForecast, String condition, double temp, String icon) {
        toDayForecastDTO.getHourlyForecast().add(
                new ToDayForecastPart(
                        hourInForecast,
                        condition,
                        temp + "˚",
                        icon
                ));
    }

}
