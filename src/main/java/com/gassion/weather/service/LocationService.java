package com.gassion.weather.service;

import com.gassion.weather.dto.LocationResponseFromApiDTO;
import com.gassion.weather.entity.Location;
import com.gassion.weather.entity.User;

import java.util.List;

public interface LocationService {
    List<LocationResponseFromApiDTO> loadByName(String locationName);
    Location saveUserToLocation(Location newLocation, User user);
    void markSavedLocation(List<LocationResponseFromApiDTO> locations, long userId);
    void deleteLocationFromId(Integer id);
}