package com.example.travelsystem.controller;

import com.example.travelsystem.model.City;
import com.example.travelsystem.service.CityService;
import com.example.travelsystem.service.CountryService;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/cities")
public class CityController {

    private final CityService cityService;
    private final CountryService countryService;

    public CityController(CityService cityService, CountryService countryService) {
        this.cityService = cityService;
        this.countryService = countryService;
    }

    @GetMapping
    public String getAllCities(Model model, Authentication authentication) {

        model.addAttribute("cities", cityService.getAllCities());

        boolean isAdmin = authentication != null &&
                authentication.getAuthorities()
                        .stream()
                        .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));

        model.addAttribute("isAdmin", isAdmin);

        return "cities";
    }

    @GetMapping("/detail/{id}")
    public String cityDetail(@PathVariable Long id, Model model, Authentication authentication) {
        City city = cityService.findCityById(id);

        if (city == null) {
            return "redirect:/admin/cities";
        }

        boolean isAdmin = authentication != null &&
                authentication.getAuthorities()
                        .stream()
                        .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));

        model.addAttribute("city", city);
        model.addAttribute("isAdmin", isAdmin);

        return "city-detail";
    }

    @GetMapping("/create")
    public String createCity(Model model) {
        model.addAttribute("city", new City());
        model.addAttribute("countries", countryService.getAllCountries());
        return "create-city";
    }

    @PostMapping("/save")
    public String saveCity(@Valid @ModelAttribute("city") City city,
                           BindingResult result,
                           Model model) {

        if (result.hasErrors()) {
            model.addAttribute("countries", countryService.getAllCountries());
            return "create-city";
        }

        cityService.saveCity(city);
        return "redirect:/admin/cities";
    }

    @GetMapping("/edit/{id}")
    public String editCity(@PathVariable Long id, Model model) {

        City city = cityService.findCityById(id);

        if (city == null) {
            return "redirect:/admin/cities";
        }

        model.addAttribute("city", city);
        model.addAttribute("countries", countryService.getAllCountries());

        return "update-city";
    }

    @PostMapping("/update")
    public String updateCity(@Valid @ModelAttribute("city") City city,
                             BindingResult result,
                             Model model) {

        if (result.hasErrors()) {
            model.addAttribute("countries", countryService.getAllCountries());
            return "update-city";
        }

        cityService.updateCity(city);
        return "redirect:/admin/cities";
    }

    @PostMapping("/delete/{id}")
    public String deleteCity(@PathVariable Long id) {

        cityService.deleteCityById(id);

        return "redirect:/admin/cities";
    }
}