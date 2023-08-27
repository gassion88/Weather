package com.gassion.weather.service.implementation;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gassion.weather.dto.forecast.CurrentWeatherDTO;
import com.gassion.weather.dto.forecast.ForecastApiResponse;
import com.gassion.weather.service.ForecastService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

@Service
public class ForecastServiceImpl implements ForecastService {
    @Value("${api.forecast.url}")
    private String API_URL;

    @Value("${api.forecast.key}")
    private String FORECAST_API_KEY;

    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;

    public ForecastServiceImpl() {
        this.objectMapper = new ObjectMapper();
        this.httpClient = HttpClient.newHttpClient();
    }

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
        return new CurrentWeatherDTO(locationName, forecastApiResponse.getCurrentWeather().getCondition(), forecastApiResponse.getCurrentWeather().getTemp());
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
