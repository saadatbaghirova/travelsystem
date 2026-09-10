package com.example.travelsystem.service.ServiceImpl;

import com.example.travelsystem.model.ContactInfo;
import com.example.travelsystem.repository.ContactInfoRepository;
import com.example.travelsystem.service.ContactInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service

public class ContactInfoServiceImpl implements ContactInfoService {

    private final ContactInfoRepository contactInfoRepository;
    public ContactInfoServiceImpl(ContactInfoRepository contactInfoRepository) {
        this.contactInfoRepository = contactInfoRepository;
    }

    @Override
    public ContactInfo getContactInfo() {
        return contactInfoRepository.findAll()
                .stream()
                .findFirst()
                .orElse(new ContactInfo());
    }

    @Override
    public ContactInfo save(ContactInfo contactInfo) {
        return contactInfoRepository.save(contactInfo);
    }
}