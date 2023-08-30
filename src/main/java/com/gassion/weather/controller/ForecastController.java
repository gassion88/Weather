package com.gassion.weather.controller;

import com.gassion.weather.dto.forecast.CurrentWeatherDTO;
import com.gassion.weather.dto.forecast.ForecastApiResponse;
import com.gassion.weather.dto.forecast.ToDayForecastDTO;
import com.gassion.weather.entity.Location;
import com.gassion.weather.service.ForecastService;
import com.gassion.weather.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("forecast")
public class ForecastController {
    private final ForecastService forecastService;
    private final LocationService locationService;

    @Autowired
    public ForecastController(ForecastService forecastService, LocationService locationService) {
        this.forecastService = forecastService;
        this.locationService = locationService;
    }

    @GetMapping("/{location_id}")
    public String loadLocationForecast(@PathVariable int location_id, Model model) {
        Location location = locationService.getById(location_id, null);
        ForecastApiResponse forecastApiResponse = forecastService.loadForecastByCoordinates(
                location.getLatitude().toString(),
                location.getLongitude().toString());

        CurrentWeatherDTO currentWeather = forecastService.getCurrentWeather(forecastApiResponse, location.getName());
        ToDayForecastDTO toDayForecastDTO = forecastService.getToDayForecast(forecastApiResponse);

        model.addAttribute("current", currentWeather);
        model.addAttribute("forecasts", toDayForecastDTO);
        return "forecast";
    }
}
