package com.gassion.weather.service.implementation;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gassion.weather.dto.LocationResponseFromApiDTO;
import com.gassion.weather.service.LocationLoaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

@Service
public class LocationLoaderServiceImpl implements LocationLoaderService {
    private final HttpClient httpClient = HttpClient.newHttpClient();
    private final ObjectMapper objectMapper;

    @Value("${api.location.url}")
    private String BASE_API_URL;

    @Value("${api.location.suffix.url}")
    private String LOCATION_SUFFIX_URL;

    @Value("${api.location.key}")
    private String LOCATION_API_KEY;

    @Autowired
    public LocationLoaderServiceImpl(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public List<LocationResponseFromApiDTO> loadByName(String locationName) {
        System.out.println(BASE_API_URL);
        try {
            URI uri = buildUriForLocationName(locationName);
            HttpRequest request = buildRequestForUriAndApiKey(uri, LOCATION_API_KEY);
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            objectMapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
            return objectMapper.readValue(response.body(),  new TypeReference<List<LocationResponseFromApiDTO>>(){});
        } catch (InterruptedException | IOException e) {
            throw new RuntimeException(e);
        }
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
