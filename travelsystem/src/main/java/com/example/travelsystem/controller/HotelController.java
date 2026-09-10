package com.example.travelsystem.controller;

import com.example.travelsystem.model.Hotel;
import com.example.travelsystem.service.AmenityService;
import com.example.travelsystem.service.CityService;
import com.example.travelsystem.service.HotelService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/hotels")
public class HotelController {

    private final HotelService hotelService;
    private final CityService cityService;
    private final AmenityService amenityService;

    public HotelController(HotelService hotelService,
                           CityService cityService,
                           AmenityService amenityService) {

        this.hotelService = hotelService;
        this.cityService = cityService;
        this.amenityService = amenityService;
    }

    @GetMapping
    public String getAllHotels(@RequestParam(defaultValue = "0") int page,
                               @RequestParam(defaultValue = "5") int size,
                               Model model) {

        Page<Hotel> hotelPage = hotelService.getHotels(PageRequest.of(page, size));

        model.addAttribute("hotelPage", hotelPage);
        model.addAttribute("hotels", hotelPage.getContent());
        model.addAttribute("currentPage", page);

        return "hotels";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {

        model.addAttribute("hotel", new Hotel());
        model.addAttribute("cities", cityService.getAllCities());
        model.addAttribute("amenities", amenityService.getAllAmenities());

        return "hotel-form";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id,
                               Model model) {

        Hotel hotel = hotelService.findHotelById(id);

        if (hotel == null) {
            return "redirect:/admin/hotels";
        }

        model.addAttribute("hotel", hotel);
        model.addAttribute("cities", cityService.getAllCities());
        model.addAttribute("amenities", amenityService.getAllAmenities());

        return "hotel-form";
    }

    @GetMapping("/{id}")
    public String findHotelById(@PathVariable Long id,
                                Model model) {

        Hotel hotel = hotelService.findHotelById(id);

        if (hotel == null) {
            return "redirect:/admin/hotels";
        }

        model.addAttribute("hotel", hotel);

        return "hotel-detail";
    }

    @PostMapping("/save")
    public String saveHotel(@Valid @ModelAttribute("hotel") Hotel hotel,
                            BindingResult result,
                            Model model) {

        if (result.hasErrors()) {

            model.addAttribute("cities", cityService.getAllCities());
            model.addAttribute("amenities", amenityService.getAllAmenities());

            return "hotel-form";
        }

        hotelService.saveHotel(hotel);

        return "redirect:/admin/hotels";
    }

    @PostMapping("/update")
    public String updateHotel(@Valid @ModelAttribute("hotel") Hotel hotel,
                              BindingResult result,
                              Model model) {

        if (result.hasErrors()) {

            model.addAttribute("cities", cityService.getAllCities());
            model.addAttribute("amenities", amenityService.getAllAmenities());

            return "hotel-form";
        }

        hotelService.updateHotel(hotel);

        return "redirect:/admin/hotels";
    }

    @PostMapping("/delete/{id}")
    public String deleteHotel(@PathVariable Long id) {

        hotelService.deleteHotelById(id);

        return "redirect:/admin/hotels";
    }

    @GetMapping("/search")
    public String searchHotels(@RequestParam String name,
                               Model model) {

        model.addAttribute("hotels", hotelService.searchHotels(name));

        return "hotels";
    }

    @GetMapping("/city")
    public String findHotelsByCity(@RequestParam String cityName,
                                   Model model) {

        model.addAttribute("hotels", hotelService.findHotelsByCity(cityName));

        return "hotels";
    }

    @GetMapping("/country")
    public String findHotelsByCountry(@RequestParam String countryName,
                                      Model model) {

        model.addAttribute("hotels", hotelService.findHotelsByCountry(countryName));

        return "hotels";
    }

}