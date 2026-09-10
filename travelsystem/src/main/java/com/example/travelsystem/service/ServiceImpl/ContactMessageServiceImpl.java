package com.example.travelsystem.service.ServiceImpl;


import com.example.travelsystem.model.ContactMessage;
import com.example.travelsystem.repository.ContactMessageRepository;
import com.example.travelsystem.service.ContactMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class ContactMessageServiceImpl implements ContactMessageService {

    private final ContactMessageRepository contactMessageRepository;
    public ContactMessageServiceImpl(ContactMessageRepository contactMessageRepository) {
        this.contactMessageRepository = contactMessageRepository;
    }

    @Override
    public ContactMessage save(ContactMessage contactMessage) {
        return contactMessageRepository.save(contactMessage);
    }

    @Override
    public List<ContactMessage> findAll() {
        return contactMessageRepository.findAll();
    }

    @Override
    public ContactMessage findById(Long id) {
        return contactMessageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Message not found"));
    }

    @Override
    public void deleteById(Long id) {
        contactMessageRepository.deleteById(id);
    }
}
