package com.example.travelsystem.controller;

import com.example.travelsystem.model.Amenity;
import com.example.travelsystem.service.AmenityService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/amenities")
public class AmenityController {

    private final AmenityService amenityService;

    public AmenityController(AmenityService amenityService) {
        this.amenityService = amenityService;
    }

    // Bütün imkanlar
    @GetMapping
    public String getAllAmenities(Model model) {

        model.addAttribute("amenities", amenityService.getAllAmenities());

        return "amenities";
    }

    // Yeni imkan əlavə et
    @GetMapping("/create")
    public String createAmenity(Model model) {

        model.addAttribute("amenity", new Amenity());

        return "create-amenity";
    }

    // Saxla
    @PostMapping("/save")
    public String saveAmenity(@Valid @ModelAttribute("amenity") Amenity amenity,
                              BindingResult result) {

        if (result.hasErrors()) {
            return "create-amenity";
        }

        amenityService.saveAmenity(amenity);

        return "redirect:/admin/amenities";
    }

    // Redaktə formu
    @GetMapping("/edit/{id}")
    public String editAmenity(@PathVariable Long id,
                              Model model) {

        Amenity amenity = amenityService.findAmenityById(id);

        if (amenity == null) {
            return "redirect:/admin/amenities";
        }

        model.addAttribute("amenity", amenity);

        return "update-amenity";
    }

    // Yenilə
    @PostMapping("/update")
    public String updateAmenity(@Valid @ModelAttribute("amenity") Amenity amenity,
                                BindingResult result) {

        if (result.hasErrors()) {
            return "update-amenity";
        }

        amenityService.updateAmenity(amenity);

        return "redirect:/admin/amenities";
    }

    // Ətraflı bax
    @GetMapping("/{id}")
    public String amenityDetails(@PathVariable Long id,
                                 Model model) {

        Amenity amenity = amenityService.findAmenityById(id);

        if (amenity == null) {
            return "redirect:/admin/amenities";
        }

        model.addAttribute("amenity", amenity);

        return "amenity-details";
    }

    // Sil
    @PostMapping("/delete/{id}")
    public String deleteAmenity(@PathVariable Long id) {

        amenityService.deleteAmenityById(id);

        return "redirect:/admin/amenities";
    }
}