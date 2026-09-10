package com.example.travelsystem.service;

import com.example.travelsystem.model.ContactMessage;

import java.util.List;

public interface ContactMessageService {

    ContactMessage save(ContactMessage contactMessage);

    List<ContactMessage> findAll();

    ContactMessage findById(Long id);

    void deleteById(Long id);

}