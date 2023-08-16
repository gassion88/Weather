package com.gassion.weather.controller;

import com.gassion.weather.entity.CustomUserPrincipal;
import com.gassion.weather.entity.Location;
import com.gassion.weather.entity.User;
import com.gassion.weather.repository.UserRepository;
import com.gassion.weather.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("location")
public class LocationController {
    private final LocationService locationService;
    private final UserRepository userRepository;

    @Autowired
    public LocationController(LocationService locationService, UserRepository userRepository) {
        this.locationService = locationService;
        this.userRepository = userRepository;
    }

    @PostMapping("/save")
    public String saveLocation(@ModelAttribute("newLocation") Location location, @AuthenticationPrincipal UserDetails userDetails) {
        User user = ((CustomUserPrincipal) userDetails).getUser();
        locationService.saveUserToLocation(location, user);

        return "search";
    }
}
