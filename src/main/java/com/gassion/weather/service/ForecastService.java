package com.gassion.weather.service;

import com.gassion.weather.dto.yandex_api.forecast.CurrentWeatherDTO;
import com.gassion.weather.dto.yandex_api.forecast.ForecastApiResponseDTO;
import com.gassion.weather.dto.yandex_api.forecast.ToDayForecastDTO;

public interface ForecastService {

    ForecastApiResponseDTO loadForecastByCoordinates(String lot, String lon);

    CurrentWeatherDTO getCurrentWeather(ForecastApiResponseDTO forecastApiResponseDTO, String locationName);

    ToDayForecastDTO getToDayForecast(ForecastApiResponseDTO forecastApiResponseDTO);

}
