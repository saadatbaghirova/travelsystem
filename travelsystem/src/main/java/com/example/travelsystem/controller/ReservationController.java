package com.example.travelsystem.controller;

import com.example.travelsystem.model.Reservation;
import com.example.travelsystem.model.Room;
import com.example.travelsystem.model.User;
import com.example.travelsystem.service.ReservationService;
import com.example.travelsystem.service.RoomService;
import com.example.travelsystem.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/reservations")
public class ReservationController {

    private final ReservationService reservationService;
    private final UserService userService;
    private final RoomService roomService;

    public ReservationController(ReservationService reservationService,
                                 UserService userService,
                                 RoomService roomService) {
        this.reservationService = reservationService;
        this.userService = userService;
        this.roomService = roomService;
    }
//    @GetMapping("/edit/{id}")
//    public String editReservation(@PathVariable Long id, Model model) {
//
//        Reservation reservation = reservationService.findReservationById(id);
//
//        if (reservation == null) {
//            return "redirect:/admin/reservations";
//        }
//
//        model.addAttribute("reservation", reservation);
//        model.addAttribute("reservations", reservationService.getAllReservations());
//        model.addAttribute("users", userService.getAllUsers());
//        model.addAttribute("rooms", roomService.getAllRooms());
//
//        return "reservations";
//    }
@GetMapping("/edit/{id}")
public String editReservation(@PathVariable Long id, Model model) {

    System.out.println("===== EDIT REQUEST =====");
    System.out.println("Reservation ID = " + id);

    Reservation reservation = reservationService.findReservationById(id);

    if (reservation == null) {
        System.out.println("Reservation tapılmadı");
        return "redirect:/admin/reservations";
    }

    System.out.println("Reservation tapıldı");

    model.addAttribute("reservation", reservation);
    model.addAttribute("reservations", reservationService.getAllReservations());
    model.addAttribute("users", userService.getAllUsers());
    model.addAttribute("rooms", roomService.getAllRooms());

    return "reservations";
}
    @GetMapping
    public String getAllReservations(Model model) {

        model.addAttribute("reservations", reservationService.getAllReservations());
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("rooms", roomService.getAllRooms());

        if (!model.containsAttribute("reservation")) {
            model.addAttribute("reservation", new Reservation());
        }

        return "reservations";
    }

    @PostMapping("/save")
    public String saveReservation(
            @RequestParam Long userId,
            @RequestParam Long roomId,
            @Valid @ModelAttribute("reservation") Reservation reservation,
            BindingResult result,
            Model model) {

        if (result.hasErrors()) {

            model.addAttribute("reservations", reservationService.getAllReservations());
            model.addAttribute("users", userService.getAllUsers());
            model.addAttribute("rooms", roomService.getAllRooms());

            return "reservations";
        }

        User user = userService.findUserById(userId);
        Room room = roomService.findRoomById(roomId);

        if (user == null || room == null) {
            return "redirect:/admin/reservations";
        }

        reservation.setUser(user);
        reservation.setRoom(room);

        reservationService.saveReservation(reservation);

        return "redirect:/admin/reservations";
    }
    @PostMapping("/update")
    public String updateReservation(
            @RequestParam Long userId,
            @RequestParam Long roomId,
            @Valid @ModelAttribute("reservation") Reservation reservation,
            BindingResult result,
            Model model) {

        if (result.hasErrors()) {
            model.addAttribute("reservations", reservationService.getAllReservations());
            model.addAttribute("users", userService.getAllUsers());
            model.addAttribute("rooms", roomService.getAllRooms());
            return "reservations";
        }

        Reservation existingReservation =
                reservationService.findReservationById(reservation.getId());

        if (existingReservation == null) {
            return "redirect:/admin/reservations";
        }

        User user = userService.findUserById(userId);
        Room room = roomService.findRoomById(roomId);

        existingReservation.setUser(user);
        existingReservation.setRoom(room);
        existingReservation.setCheckInDate(reservation.getCheckInDate());
        existingReservation.setCheckOutDate(reservation.getCheckOutDate());
        existingReservation.setReservationDate(reservation.getReservationDate());
        existingReservation.setTotalPrice(reservation.getTotalPrice());

        reservationService.updateReservation(existingReservation);

        return "redirect:/admin/reservations";
    }

    @PostMapping("/delete/{id}")
    public String deleteReservation(@PathVariable Long id) {

        reservationService.deleteReservationById(id);
        return "redirect:/admin/reservations";
    }
}