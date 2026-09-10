package com.example.travelsystem.controller;
import com.example.travelsystem.model.About;
import com.example.travelsystem.service.AboutService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AboutController {

    private final AboutService aboutService;

    public AboutController(AboutService aboutService) {
        this.aboutService = aboutService;
    }

    // Public About səhifəsi
    @GetMapping("/about")
    public String getAbout(Model model) {

        model.addAttribute("about", aboutService.getAbout());

        return "about";
    }

    // Admin edit səhifəsi
    @GetMapping("/admin/about/edit")
    public String editAbout(Model model) {

        model.addAttribute("about", aboutService.getAbout());

        return "about-edit";
    }

    // About məlumatını yenilə
    @PostMapping("/admin/about/save")
    public String saveAbout(@ModelAttribute About about) {

        aboutService.save(about);

        return "redirect:/about";
    }

    // About sil
    @PostMapping("/admin/about/delete")
    public String deleteAbout() {

        // Əgər AboutService-də delete metodu varsa:
        // aboutService.delete();

        return "redirect:/about";
    }
}