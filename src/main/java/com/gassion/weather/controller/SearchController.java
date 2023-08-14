package com.gassion.weather.controller;

import com.gassion.weather.dto.LocationResponseFromApiDTO;
import com.gassion.weather.entity.Location;
import com.gassion.weather.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
@RequestMapping("/search")
public class SearchController {
    private final LocationService locationService;

    @Autowired
    public SearchController(LocationService locationService) {
        this.locationService = locationService;
    }

    @GetMapping
    public String locationSearch(@ModelAttribute("startString") String startString, Model model) {
        List<LocationResponseFromApiDTO> locations = locationService.loadByName(startString);
        model.addAttribute("locations", locations);
        model.addAttribute("newLocation", new Location());

        return "search";
    }
}
