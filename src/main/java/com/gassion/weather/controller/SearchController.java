package com.gassion.weather.controller;

import com.gassion.weather.dto.LocationResponseFromApiDTO;
import com.gassion.weather.entity.CustomUserPrincipal;
import com.gassion.weather.entity.Location;
import com.gassion.weather.entity.User;
import com.gassion.weather.service.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
@RequestMapping("/search")
@RequiredArgsConstructor
public class SearchController {

    private final LocationService locationService;

    @GetMapping
    public String locationSearch(@ModelAttribute("startString") String startString, Model model, @AuthenticationPrincipal UserDetails userDetails) {
        User user = ((CustomUserPrincipal) userDetails).getUser();

        List<LocationResponseFromApiDTO> locations = locationService.loadByName(startString);
        locationService.markSavedLocation(locations, user.getId());

        model.addAttribute("locations", locations);
        model.addAttribute("newLocation", new Location());

        return "search";
    }
}
