package com.example.travelsystem.controller;

import com.example.travelsystem.model.Country;
import com.example.travelsystem.service.CountryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class CountryViewController {

    private final CountryService countryService;

    public CountryViewController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping("/country/{id}/cities")
    public String showCities(@PathVariable Long id,
                             Model model) {

        Country country = countryService.getCountryById(id);

        if (country == null) {
            return "redirect:/countries";
        }

        model.addAttribute("country", country);
        model.addAttribute("cities", country.getCities());

        return "country-cities";
    }
}