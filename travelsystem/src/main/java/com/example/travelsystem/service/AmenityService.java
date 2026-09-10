package com.example.travelsystem.service;

import com.example.travelsystem.model.Amenity;

import java.util.List;

public interface AmenityService {
    Amenity saveAmenity(Amenity amenity);

    List<Amenity> getAllAmenities();

    Amenity findAmenityById(Long id);

    Amenity updateAmenity(Amenity amenity);

    void deleteAmenityById(Long id);
}
