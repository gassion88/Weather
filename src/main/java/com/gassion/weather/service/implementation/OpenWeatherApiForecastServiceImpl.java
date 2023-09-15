package com.gassion.weather.service.implementation;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gassion.weather.dto.ForecastApiResponseDTO;
import com.gassion.weather.dto.CurrentWeatherDTO;
import com.gassion.weather.dto.open_weather_api.forecast.OpenWeatherForecastApiResponseDTO;
import com.gassion.weather.dto.ToDayForecastDTO;
import com.gassion.weather.service.ForecastService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
@Primary
@RequiredArgsConstructor
public class OpenWeatherApiForecastServiceImpl implements ForecastService {

    @Value("${open_weather.api.forecast.url}")
    private String API_URL;

    @Value("${open_weather.api.forecast.key}")
    private String API_ID;

    private final HttpClient httpClient;

    private final ObjectMapper objectMapper;
    @Override
    public ForecastApiResponseDTO loadForecastByCoordinates(String lat, String lon) {
        try {
            URI uri = buildUriForCoordinates(lat, lon);
            HttpRequest request = buildRequestForUri(uri);
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            objectMapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);

            return objectMapper.readValue(response.body(), new TypeReference<OpenWeatherForecastApiResponseDTO>() {});
        } catch (InterruptedException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public CurrentWeatherDTO getCurrentWeather(ForecastApiResponseDTO ForecastApiResponseDTO, String locationName) {
        return null;
    }

    @Override
    public ToDayForecastDTO getToDayForecast(ForecastApiResponseDTO ForecastApiResponseDTO) {
        return null;
    }

    private URI buildUriForCoordinates(String lat, String lon) {
        return URI.create(
                API_URL +
                        "lat=" + lat +
                        "&lon=" + lon +
                        "&appid=" + API_ID);
    }

    private static HttpRequest buildRequestForUri(URI uri) {
        return HttpRequest.newBuilder()
                .uri(uri)
                .GET()
                .build();
    }
}
