package com.example.travelsystem.service.ServiceImpl;
import com.example.travelsystem.model.Amenity;
import com.example.travelsystem.repository.AmenityRepository;
import com.example.travelsystem.service.AmenityService;
import org.springframework.stereotype.Service;
import java.util.List;


    @Service
    public class AmenityServiceImpl implements AmenityService {
        private final AmenityRepository amenityRepository;

        public AmenityServiceImpl(AmenityRepository amenityRepository) {
            this.amenityRepository = amenityRepository;
        }


        @Override
        public Amenity saveAmenity(Amenity amenity) {
            return amenityRepository.save(amenity);
        }

        @Override
        public List<Amenity> getAllAmenities() {
            return amenityRepository.findAll();
        }

        @Override
        public Amenity findAmenityById(Long id) {
            return amenityRepository.findById(id).orElse(null);
        }

        @Override
        public Amenity updateAmenity(Amenity amenity) {
            return amenityRepository.save(amenity);
        }

        @Override
        public void deleteAmenityById(Long id) {
            amenityRepository.deleteById(id);
        }
    }

