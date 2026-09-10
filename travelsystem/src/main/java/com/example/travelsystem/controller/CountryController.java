package com.example.travelsystem.controller;

import com.example.travelsystem.model.Country;
import com.example.travelsystem.service.CountryService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/countries")
public class CountryController {

    private final CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping
    public String getAllCountries(Model model) {

        model.addAttribute("countries", countryService.getAllCountries());

        return "countries";
    }

    @GetMapping("/create")
    public String createCountry(Model model) {

        model.addAttribute("country", new Country());

        return "create-country";
    }

    @PostMapping("/save")
    public String saveCountry(@Valid @ModelAttribute("country") Country country,
                              BindingResult result) {

        if (result.hasErrors()) {
            return "create-country";
        }

        countryService.saveCountry(country);

        return "redirect:/admin/countries";
    }

    @GetMapping("/edit/{id}")
    public String editCountry(@PathVariable Long id,
                              Model model) {

        Country country = countryService.getCountryById(id);

        if (country == null) {
            return "redirect:/admin/countries";
        }

        model.addAttribute("country", country);

        return "update-country";
    }

    @GetMapping("/{id}")
    public String countryDetails(@PathVariable Long id,
                                 Model model) {

        Country country = countryService.getCountryById(id);

        if (country == null) {
            return "redirect:/admin/countries";
        }

        model.addAttribute("country", country);

        return "country-details";
    }

    @PostMapping("/update")
    public String updateCountry(@Valid @ModelAttribute("country") Country country,
                                BindingResult result) {

        if (result.hasErrors()) {
            return "update-country";
        }

        countryService.updateCountry(country);

        return "redirect:/admin/countries";
    }

    @PostMapping("/delete/{id}")
    public String deleteCountry(@PathVariable Long id) {

        countryService.deleteCountryById(id);

        return "redirect:/admin/countries";
    }
}