package com.gassion.weather.service;

import com.gassion.weather.dto.ForecastApiResponseDTO;
import com.gassion.weather.dto.CurrentWeatherDTO;
import com.gassion.weather.dto.ToDayForecastDTO;

public interface ForecastService {

    ForecastApiResponseDTO loadForecastByCoordinates(String lot, String lon);

    CurrentWeatherDTO getCurrentWeather(ForecastApiResponseDTO yandexApiForecastApiResponseDTO, String locationName);

    ToDayForecastDTO getToDayForecast(ForecastApiResponseDTO yandexApiForecastApiResponseDTO);

}
