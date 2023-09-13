package com.gassion.weather.service;

import com.gassion.weather.dto.forecast.CurrentWeatherDTO;
import com.gassion.weather.dto.forecast.ForecastApiResponse;
import com.gassion.weather.dto.forecast.ToDayForecastDTO;

public interface ForecastService {

    ForecastApiResponse loadForecastByCoordinates(String lot, String lon);

    CurrentWeatherDTO getCurrentWeather(ForecastApiResponse forecastApiResponse, String locationName);

    ToDayForecastDTO getToDayForecast(ForecastApiResponse forecastApiResponse);

}
