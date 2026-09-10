package com.example.travelsystem.repository;



import com.example.travelsystem.model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long> {

    List<Hotel> findByNameContainingIgnoreCase(String name);

    List<Hotel> findByCity_NameIgnoreCase(String cityName);

    List<Hotel> findByCity_Country_NameIgnoreCase(String countryName);

}