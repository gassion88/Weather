package com.gassion.weather.service.implementation;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gassion.weather.dto.forecast.CurrentWeatherDTO;
import com.gassion.weather.dto.forecast.ForecastApiResponse;
import com.gassion.weather.dto.forecast.ToDayForecastDTO;
import com.gassion.weather.dto.forecast.section.Forecast;
import com.gassion.weather.dto.forecast.section.ToDayForecastPart;
import com.gassion.weather.dto.forecast.section.parts.Hour;
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
public class ForecastServiceImpl implements ForecastService {
    @Value("${api.forecast.url}")
    private String API_URL;

    @Value("${api.forecast.key}")
    private String FORECAST_API_KEY;

    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;

    @Override
    public ForecastApiResponse loadForecastByCoordinates(String lat, String lon) {
        try {
            URI uri = buildUriForCoordinates(lat, lon);
            HttpRequest request = buildRequestForUriAndApiKey(uri, FORECAST_API_KEY);
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            objectMapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
            return objectMapper.readValue(response.body(), new TypeReference<List<ForecastApiResponse>>() {}).get(0);
        } catch (InterruptedException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public CurrentWeatherDTO getCurrentWeather(ForecastApiResponse forecastApiResponse, String locationName) {
        return new CurrentWeatherDTO(
                locationName,
                forecastApiResponse.getCurrentWeather().getCondition(),
                forecastApiResponse.getCurrentWeather().getTemp(), forecastApiResponse.getCurrentWeather().getFeelsLike(),
                forecastApiResponse.getCurrentWeather().getPressureMM(),
                forecastApiResponse.getCurrentWeather().getWindSpeed(),
                forecastApiResponse.getCurrentWeather().getHumidity());
    }

    @Override
    public ToDayForecastDTO getToDayForecast(ForecastApiResponse forecastApiResponse) {
        ToDayForecastDTO toDayForecastDTO = new ToDayForecastDTO();

        Instant instant = Instant.now();
        ZoneId z = ZoneId.of( "Europe/Moscow" );
        ZonedDateTime zdt = instant.atZone(z);
        int currentHour = zdt.getHour();

        DecimalFormat formatter = new DecimalFormat("00");
        int hoursCount = 0;

        for(Forecast forecastDays : forecastApiResponse.getForecast()) {
            if(hoursCount == 8) break;

            for(Hour hour : forecastDays.getHours()) {
                int hourInForecast = Integer.parseInt(hour.getHour());

                if(hoursCount == 0 && hourInForecast <= currentHour) continue;

                   toDayForecastDTO.getHourlyForecast().add(
                           new ToDayForecastPart(
                                   formatter.format(hourInForecast) + ":00",
                                   hour.getCondition(),
                                   hour.getTemp() + "˚"
                           ));
                   hoursCount++;

                if(hoursCount == 8) break;
            }
        }

        return toDayForecastDTO;
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
}
