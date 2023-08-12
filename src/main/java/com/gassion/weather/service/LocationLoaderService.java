package com.gassion.weather.service;

import com.gassion.weather.dto.LocationResponseFromApiDTO;

import java.io.IOException;
import java.util.List;

public interface LocationLoaderService {

    List<LocationResponseFromApiDTO> loadByName(String locationName);
}
