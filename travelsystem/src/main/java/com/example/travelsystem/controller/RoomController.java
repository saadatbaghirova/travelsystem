package com.example.travelsystem.controller;

import com.example.travelsystem.model.Room;
import com.example.travelsystem.service.HotelService;
import com.example.travelsystem.service.RoomService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin/rooms")
public class RoomController {

    private final RoomService roomService;
    private final HotelService hotelService;

    public RoomController(RoomService roomService,
                          HotelService hotelService) {
        this.roomService = roomService;
        this.hotelService = hotelService;
    }

    @GetMapping
    public String getAllRooms(Model model) {

        if (!model.containsAttribute("room")) {
            model.addAttribute("room", new Room());
        }

        model.addAttribute("rooms", roomService.getAllRooms());
        model.addAttribute("hotels", hotelService.getAllHotels());

        return "rooms";
    }

    @PostMapping("/save")
    public String saveRoom(@Valid @ModelAttribute("room") Room room,
                           BindingResult result,
                           RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {

            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.room",
                    result);

            redirectAttributes.addFlashAttribute("room", room);
            redirectAttributes.addFlashAttribute("openRoomModal", true);
            redirectAttributes.addFlashAttribute("modalMode", "create");

            return "redirect:/admin/rooms";
        }

        roomService.saveRoom(room);

        return "redirect:/admin/rooms";
    }

    @PostMapping("/update")
    public String updateRoom(@Valid @ModelAttribute("room") Room room,
                             BindingResult result,
                             RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {

            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.room",
                    result);

            redirectAttributes.addFlashAttribute("room", room);
            redirectAttributes.addFlashAttribute("openRoomModal", true);
            redirectAttributes.addFlashAttribute("modalMode", "edit");

            return "redirect:/admin/rooms";
        }

        roomService.updateRoom(room);

        return "redirect:/admin/rooms";
    }

    @PostMapping("/delete/{id}")
    public String deleteRoom(@PathVariable Long id) {

        roomService.deleteRoomById(id);

        return "redirect:/admin/rooms";
    }

    @GetMapping("/{id}")
    public String roomDetails(@PathVariable Long id,
                              Model model) {

        Room room = roomService.findRoomById(id);

        if (room == null) {
            return "redirect:/admin/rooms";
        }

        model.addAttribute("room", room);

        return "room-details";
    }

    @GetMapping("/price")
    public String findRoomsByPrice(@RequestParam Double minPrice,
                                   @RequestParam Double maxPrice,
                                   Model model) {

        model.addAttribute("rooms",
                roomService.findRoomsByPrice(minPrice, maxPrice));

        model.addAttribute("hotels", hotelService.getAllHotels());
        model.addAttribute("room", new Room());

        return "rooms";
    }
}