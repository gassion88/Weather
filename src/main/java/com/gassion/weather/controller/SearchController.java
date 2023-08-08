package com.gassion.weather.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;
import java.util.Collections;

@Controller
@RequestMapping("/search")
public class SearchController {
    @GetMapping
    public String showSearchResult(@ModelAttribute("startString") String startString, Model model) {
        model.addAttribute("city", Collections.EMPTY_LIST);
        return "search";
    }
}
