package com.gassion.weather.service;

import com.gassion.weather.dto.LocationResponseFromApiDTO;

import java.util.List;

public interface LocationService {

    List<LocationResponseFromApiDTO> loadByName(String locationName);
}
