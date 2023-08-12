package com.gassion.weather.controller;

import com.gassion.weather.dto.LocationResponseFromApiDTO;
import com.gassion.weather.service.LocationLoaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.List;


@Controller
@RequestMapping("/search")
public class SearchController {
    private final LocationLoaderService locationLoaderService;

    @Autowired
    public SearchController(LocationLoaderService locationLoaderService) {
        this.locationLoaderService = locationLoaderService;
    }

    @GetMapping
    public String showSearchResult(@ModelAttribute("startString") String startString, Model model) {
        List<LocationResponseFromApiDTO> locations = locationLoaderService.loadByName(startString);
        model.addAttribute("locations", locations);

        return "search";
    }
}
