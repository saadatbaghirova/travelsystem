package com.example.travelsystem.controller;

import com.example.travelsystem.enums.RoleEnum;

import com.example.travelsystem.model.User;
import com.example.travelsystem.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    // Login səhifəsi
    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    // Register səhifəsi
    @GetMapping("/register")
    public String registerPage(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    // Register əməliyyatı
    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user) {

        user.setRole(RoleEnum.ROLE_USER);

        userService.saveUser(user);

        return "redirect:/auth/login";
    }

}