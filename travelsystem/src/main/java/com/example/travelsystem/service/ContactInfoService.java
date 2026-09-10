package com.example.travelsystem.service;

import com.example.travelsystem.model.ContactInfo;

public interface ContactInfoService {

    ContactInfo getContactInfo();

    ContactInfo save(ContactInfo contactInfo);
}
