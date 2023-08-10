package com.gassion.weather.service.implementation;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gassion.weather.dto.LocationResponseFromApiDTO;
import com.gassion.weather.service.LocationLoaderService;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class LocationLoaderServiceImpl implements LocationLoaderService {
    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;

    @Value("${location_api_url}")
    private static String BASE_API_URL;

    @Value("${location_suffix_url}")
    private static String LOCATION_SUFFIX_URL;

    @Value("${location_api_key}")
    private static String LOCATION_API_KEY;

    public LocationLoaderServiceImpl(HttpClient httpClient, ObjectMapper objectMapper) {
        this.httpClient = httpClient;
        this.objectMapper = objectMapper;
    }

    @Override
    public List<LocationResponseFromApiDTO> loadByName(String locationName) throws IOException, InterruptedException {
        URI uri = buildUriForLocationName(locationName);
        HttpRequest request = buildRequestForUriAndApiKey(uri, LOCATION_API_KEY);
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        return objectMapper.readValue(response.body(), new TypeReference<List<LocationResponseFromApiDTO>>(){});
    }

    private static HttpRequest buildRequestForUriAndApiKey(URI uri, String locationApiKey) {
        return HttpRequest.newBuilder()
                .uri(uri)
                .header("Authorization", "Bearer " + locationApiKey)
                .GET()
                .build();
    }

    private URI buildUriForLocationName(String locationName) {
        return URI.create(BASE_API_URL+LOCATION_SUFFIX_URL + "?searchTerm=" + locationName);
    }
}
