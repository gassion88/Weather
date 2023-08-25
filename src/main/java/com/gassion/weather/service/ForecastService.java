package com.gassion.weather.service;

import com.gassion.weather.dto.LocationResponseFromApiDTO;
import com.gassion.weather.dto.forecast.ForecastApiResponse;

import java.util.List;

public interface ForecastService {
    ForecastApiResponse loadForecastByCoordinates(String lot, String lon);
}
