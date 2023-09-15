package com.gassion.weather.controller;

import com.gassion.weather.dto.CurrentWeatherDTO;
import com.gassion.weather.dto.ForecastApiResponseDTO;
import com.gassion.weather.dto.yandex_api.forecast.YandexApiForecastApiResponseDTO;
import com.gassion.weather.dto.ToDayForecastDTO;
import com.gassion.weather.entity.Location;
import com.gassion.weather.service.ForecastService;
import com.gassion.weather.service.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("forecast")
@RequiredArgsConstructor
public class ForecastController {

    private final ForecastService forecastService;

    private final LocationService locationService;

    @GetMapping("/{location_id}")
    public String loadLocationForecast(@PathVariable int location_id, Model model) {
        Location location = locationService.getById(location_id, null);
        ForecastApiResponseDTO forecastApiResponseDTO = forecastService.loadForecastByCoordinates(
                location.getLatitude().toString(),
                location.getLongitude().toString());

        CurrentWeatherDTO currentWeather = forecastService.getCurrentWeather(forecastApiResponseDTO, location.getName());
        ToDayForecastDTO toDayForecastDTO = forecastService.getToDayForecast(forecastApiResponseDTO);

        model.addAttribute("current", currentWeather);
        model.addAttribute("forecasts", toDayForecastDTO);
        return "forecast";
    }
}
