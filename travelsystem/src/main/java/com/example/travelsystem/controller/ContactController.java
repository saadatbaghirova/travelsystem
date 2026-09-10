package com.example.travelsystem.controller;

import com.example.travelsystem.model.ContactInfo;
import com.example.travelsystem.model.ContactMessage;
import com.example.travelsystem.service.ContactInfoService;
import com.example.travelsystem.service.ContactMessageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/contact")
public class ContactController {

    private final ContactInfoService contactInfoService;
    private final ContactMessageService contactMessageService;

    public ContactController(ContactInfoService contactInfoService,
                             ContactMessageService contactMessageService) {
        this.contactInfoService = contactInfoService;
        this.contactMessageService = contactMessageService;
    }

    // Contact səhifəsi
    @GetMapping
    public String contactPage(Model model) {

        model.addAttribute("contactInfo", contactInfoService.getContactInfo());
        model.addAttribute("contactMessage", new ContactMessage());

        return "contact";
    }

    // Admin - Mesajların siyahısı
    @GetMapping("/list")
    public String listMessages(Model model) {

        model.addAttribute("contactInfo", contactInfoService.getContactInfo());
        model.addAttribute("messages", contactMessageService.findAll());

        return "contact-list";
    }

    // Contact məlumatını redaktə et
    @GetMapping("/edit")
    public String editContact(Model model) {

        ContactInfo contactInfo = contactInfoService.getContactInfo();

        if (contactInfo == null) {
            contactInfo = new ContactInfo();
        }

        model.addAttribute("contactInfo", contactInfo);

        return "contact-edit";
    }

    // Contact məlumatını saxla
    @PostMapping("/save")
    public String saveContact(@ModelAttribute ContactInfo contactInfo) {

        contactInfoService.save(contactInfo);

        return "redirect:/contact/list";
    }

    // İstifadəçi mesaj göndərir
    @PostMapping("/send")
    public String sendMessage(@ModelAttribute ContactMessage contactMessage) {

        contactMessage.setCreatedDate(LocalDateTime.now());
        contactMessage.setRead(false);

        contactMessageService.save(contactMessage);

        return "redirect:/contact";
    }

    // Mesaj detalları
    @GetMapping("/message/{id}")
    public String messageDetails(@PathVariable Long id,
                                 Model model) {

        ContactMessage message = contactMessageService.findById(id);

        if (message == null) {
            return "redirect:/contact/list";
        }

        message.setRead(true);
        contactMessageService.save(message);

        model.addAttribute("contactMessage", message);

        return "message-details";
    }

    // Mesajı sil
    @PostMapping("/delete/{id}")
    public String deleteMessage(@PathVariable Long id) {

        contactMessageService.deleteById(id);

        return "redirect:/contact/list";
    }
}