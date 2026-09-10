package com.example.travelsystem.controller;
import com.example.travelsystem.model.Room;
import com.example.travelsystem.service.AmenityService;
import com.example.travelsystem.service.CityService;
import com.example.travelsystem.service.CountryService;
import com.example.travelsystem.service.HotelService;
import com.example.travelsystem.service.RoomService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import com.example.travelsystem.service.ReservationService;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final CountryService countryService;
    private final CityService cityService;
    private final HotelService hotelService;
    private final RoomService roomService;
    private final AmenityService amenityService;
    private final ReservationService reservationService;

    public HomeController(CountryService countryService,
                          CityService cityService,
                          HotelService hotelService,
                          RoomService roomService,
                          AmenityService amenityService,
                          ReservationService reservationService) {

        this.countryService = countryService;
        this.cityService = cityService;
        this.hotelService = hotelService;
        this.roomService = roomService;
        this.amenityService = amenityService;
        this.reservationService = reservationService;
    }

    @GetMapping("/")
    public String index(Model model) {

        model.addAttribute("countries", countryService.getAllCountries());
        model.addAttribute("cities", cityService.getAllCities());
        model.addAttribute("hotels", hotelService.getAllHotels());
        model.addAttribute("rooms", roomService.getAllRooms());
        model.addAttribute("amenities", amenityService.getAllAmenities());
        model.addAttribute("reservations", reservationService.getAllReservations());

        return "index";
    }

    @GetMapping("/hotels")
    public String viewHotels(Model model) {

        model.addAttribute("hotels", hotelService.getAllHotels());

        return "hotels";
    }

    @GetMapping("/rooms")
    public String viewRooms(Model model) {

        model.addAttribute("rooms", roomService.getAllRooms());
        model.addAttribute("room", new Room());   // <-- bunu əlavə et
        model.addAttribute("hotels", hotelService.getAllHotels());

        return "rooms";
    }

    @GetMapping("/cities")
    public String viewCities(Model model) {

        model.addAttribute("cities", cityService.getAllCities());

        return "cities";
    }

    @GetMapping("/countries")
    public String viewCountries(Model model) {

        model.addAttribute("countries", countryService.getAllCountries());

        return "countries";
    }

    @GetMapping("/amenities")
    public String viewAmenities(Model model) {

        model.addAttribute("amenities", amenityService.getAllAmenities());

        return "amenities";
    }

}