package com.gassion.weather.service.implementation;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gassion.weather.dto.LocationResponseFromApiDTO;
import com.gassion.weather.entity.Location;
import com.gassion.weather.entity.User;
import com.gassion.weather.repository.LocationRepository;
import com.gassion.weather.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class LocationServiceImpl implements LocationService {
    private final LocationRepository locationRepository;
    private final HttpClient httpClient = HttpClient.newHttpClient();
    private final ObjectMapper objectMapper;

    @Value("${api.location.url}")
    private String BASE_API_URL;

    @Value("${api.location.suffix.url}")
    private String LOCATION_SUFFIX_URL;

    @Value("${api.location.key}")
    private String LOCATION_API_KEY;

    @Autowired
    public LocationServiceImpl(LocationRepository locationRepository, ObjectMapper objectMapper) {
        this.locationRepository = locationRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public List<LocationResponseFromApiDTO> loadByName(String locationName) {
        try {
            URI uri = buildUriForLocationName(locationName);
            HttpRequest request = buildRequestForUriAndApiKey(uri, LOCATION_API_KEY);
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            objectMapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
            return objectMapper.readValue(response.body(), new TypeReference<List<LocationResponseFromApiDTO>>() {});
        } catch (InterruptedException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteLocationFromId(Integer id) {
        locationRepository.deleteById(id);
    }

    @Override
    public void saveUserToLocation(Location newLocation, User user) {
        newLocation.setUser(user);
        locationRepository.save(newLocation);
    }

    public void markSavedLocation(List<LocationResponseFromApiDTO> locations, long userId) {
        for (LocationResponseFromApiDTO location : locations) {

            Optional<Location> locationOptional = getLocationIfIsSaved(location, userId);
            if (locationOptional.isPresent()) {
                location.setId(locationOptional.get().getId());
                location.setSaved(true);
            }
        }
    }

    @Override
    public List<Location> getAllUserLocation(User user) {
        return locationRepository.findAllByUser(user).orElse(Collections.emptyList());
    }

    private static HttpRequest buildRequestForUriAndApiKey(URI uri, String locationApiKey) {
        return HttpRequest.newBuilder()
                .uri(uri)
                .header("Authorization", "Bearer " + locationApiKey)
                .GET()
                .build();
    }

    private URI buildUriForLocationName(String locationName) {
        return URI.create(BASE_API_URL + LOCATION_SUFFIX_URL + "?searchTerm=" + locationName);
    }

    private Optional<Location> getLocationIfIsSaved(LocationResponseFromApiDTO location, Long userId) {
        return locationRepository.findByNameAndCountryCodeAndUserId(location.getName(), location.getCountryCode(), userId);
    }
}
