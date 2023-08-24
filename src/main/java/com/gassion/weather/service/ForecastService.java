package com.gassion.weather.service;

import com.gassion.weather.dto.LocationResponseFromApiDTO;
import com.gassion.weather.dto.forecast.ForecastApiResponse;

public interface ForecastService {
    ForecastApiResponse loadForecastByCoordinates(String lot, String lon);
}
