package com.gassion.weather.service.implementation;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gassion.weather.dto.yandex_api.forecast.CurrentWeatherDTO;
import com.gassion.weather.dto.yandex_api.forecast.ForecastApiResponseDTO;
import com.gassion.weather.dto.yandex_api.forecast.ToDayForecastDTO;
import com.gassion.weather.dto.yandex_api.forecast.section.CurrentWeather;
import com.gassion.weather.dto.yandex_api.forecast.section.Forecast;
import com.gassion.weather.dto.yandex_api.forecast.section.ToDayForecastPart;
import com.gassion.weather.dto.yandex_api.forecast.section.parts.Hour;
import com.gassion.weather.service.ForecastService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.DecimalFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class YandexApiForecastServiceImpl implements ForecastService {

    @Value("${yandex.api.forecast.url}")
    private String API_URL;

    @Value("${yandex.api.forecast.key}")
    private String FORECAST_API_KEY;

    private final HttpClient httpClient;

    private final ObjectMapper objectMapper;

    @Override
    public ForecastApiResponseDTO loadForecastByCoordinates(String lat, String lon) {
        try {
            URI uri = buildUriForCoordinates(lat, lon);
            HttpRequest request = buildRequestForUriAndApiKey(uri, FORECAST_API_KEY);
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            objectMapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
            return objectMapper.readValue(response.body(), new TypeReference<List<ForecastApiResponseDTO>>() {}).get(0);
        } catch (InterruptedException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public CurrentWeatherDTO getCurrentWeather(ForecastApiResponseDTO forecastApiResponseDTO, String locationName) {
        CurrentWeather currentWeather = forecastApiResponseDTO.getCurrentWeather();

        return new CurrentWeatherDTO(
                locationName,
                currentWeather.getCondition(),
                currentWeather.getTemp(),
                currentWeather.getFeelsLike(),
                currentWeather.getPressureMM(),
                currentWeather.getWindSpeed(),
                currentWeather.getHumidity());
    }

    @Override
    public ToDayForecastDTO getToDayForecast(ForecastApiResponseDTO forecastApiResponseDTO) {
        ToDayForecastDTO toDayForecastDTO = new ToDayForecastDTO();
        int currentHour = getCurrentHourFromZone("Europe/Moscow");
        int forecastHoursCount = 0;

        for(Forecast forecastDays : forecastApiResponseDTO.getForecast()) {
            if(forecastHoursCount == 8) break;

            for(Hour hour : forecastDays.getHours()) {
                int hourInForecast = Integer.parseInt(hour.getHour());
                if(forecastHoursCount == 0 && hourInForecast <= currentHour) continue;

                addForecastToDTO(toDayForecastDTO, hourInForecast, hour.getCondition(), hour.getTemp());
                forecastHoursCount++;

                if(forecastHoursCount == 8) break;
            }
        }

        return toDayForecastDTO;
    }

    private void addForecastToDTO(ToDayForecastDTO toDayForecastDTO, int hourInForecast, String condition, int temp) {
        toDayForecastDTO.getHourlyForecast().add(
                new ToDayForecastPart(
                        new DecimalFormat("00").format(hourInForecast) + ":00",
                        condition,
                        temp + "˚"
                ));
    }

    private static HttpRequest buildRequestForUriAndApiKey(URI uri, String forecastApiKey) {
        return HttpRequest.newBuilder()
                .uri(uri)
                .header("X-Yandex-API-Key",   forecastApiKey)
                .GET()
                .build();
    }

    private URI buildUriForCoordinates(String lat, String lon) {
        return URI.create(
                API_URL +
                "lat=" + lat +
                "&lon=" + lon +
                "&extra=true");
    }

    private int getCurrentHourFromZone(String zoneName) {
        Instant instant = Instant.now();
        ZoneId z = ZoneId.of(zoneName);
        ZonedDateTime zdt = instant.atZone(z);
        return zdt.getHour();
    }
}
