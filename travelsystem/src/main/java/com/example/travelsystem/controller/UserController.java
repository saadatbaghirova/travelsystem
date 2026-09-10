package com.example.travelsystem.controller;

import com.example.travelsystem.model.User;
import com.example.travelsystem.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Bütün istifadəçilər
    @GetMapping
    public String getAllUsers(Model model) {

        model.addAttribute("users", userService.getAllUsers());

        return "users";
    }

    // Yeni istifadəçi formu
    @GetMapping("/create")
    public String createUser(Model model) {

        model.addAttribute("user", new User());

        return "create-user";
    }

    // İstifadəçi detalları
    @GetMapping("/{id}")
    public String userDetails(@PathVariable Long id,
                              Model model) {

        User user = userService.findUserById(id);

        if (user == null) {
            return "redirect:/admin/users";
        }

        model.addAttribute("user", user);

        return "user-details";
    }

    // Redaktə formu
    @GetMapping("/edit/{id}")
    public String editUser(@PathVariable Long id,
                           Model model) {

        User user = userService.findUserById(id);

        if (user == null) {
            return "redirect:/admin/users";
        }

        model.addAttribute("user", user);

        return "update-user";
    }

    // Yeni istifadəçi əlavə et
    @PostMapping("/save")
    public String saveUser(@Valid @ModelAttribute("user") User user,
                           BindingResult result) {

        if (result.hasErrors()) {
            return "create-user";
        }

        userService.saveUser(user);

        return "redirect:/admin/users";
    }

    // Yenilə
    @PostMapping("/update")
    public String updateUser(@Valid @ModelAttribute("user") User user,
                             BindingResult result) {

        if (result.hasErrors()) {
            return "update-user";
        }

        userService.updateUser(user);

        return "redirect:/admin/users";
    }

    // Sil
    @PostMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) {

        userService.deleteUserById(id);

        return "redirect:/admin/users";
    }
}