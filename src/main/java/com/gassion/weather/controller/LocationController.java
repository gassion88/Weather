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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public String saveLocation(@ModelAttribute("newLocation") Location location, @AuthenticationPrincipal UserDetails userDetails, @RequestParam("startString") String startString) {
        User user = ((CustomUserPrincipal) userDetails).getUser();
        locationService.saveUserToLocation(location, user);

        return "redirect:/search?startString=" + startString;
    }

    @GetMapping("/{id}/delete")
    public String deleteLocation(@PathVariable("id") Integer locationId, @ModelAttribute("startString") String startString) {
        locationService.deleteLocationFromId(locationId);
        return "redirect:/";
    }

    @GetMapping("/saved")
    public String viewSavedLocations(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        User user = ((CustomUserPrincipal) userDetails).getUser();
        List<Location> locations = locationService.getAllUserLocation(user);
        model.addAttribute("locations", locations);

        return "saved_locations";
    }
}
