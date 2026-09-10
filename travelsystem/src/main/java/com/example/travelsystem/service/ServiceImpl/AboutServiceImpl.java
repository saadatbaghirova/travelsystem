package com.example.travelsystem.service.ServiceImpl;

import com.example.travelsystem.model.About;
import com.example.travelsystem.repository.AboutRepository;
import com.example.travelsystem.service.AboutService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
@RequiredArgsConstructor
public class AboutServiceImpl implements AboutService {
        @Autowired
    private final AboutRepository aboutRepository;

    @Override
    public About getAbout() {
        return aboutRepository.findAll()
                .stream()
                .findFirst()
                .orElse(new About());
    }

    @Override
    public About save(About about) {
        return aboutRepository.save(about);
    }
}

