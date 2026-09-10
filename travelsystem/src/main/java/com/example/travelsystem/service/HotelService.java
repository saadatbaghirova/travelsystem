package com.example.travelsystem.service;
import com.example.travelsystem.model.Hotel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
public interface HotelService {

    Hotel saveHotel(Hotel hotel);

    List<Hotel> getAllHotels();

    Hotel findHotelById(Long id);

    Hotel updateHotel(Hotel hotel);

    List<Hotel> searchHotels(String name);

    List<Hotel> findHotelsByCity(String cityName);

    List<Hotel> findHotelsByCountry(String countryName);

    Page<Hotel> getHotels(Pageable pageable);
    void deleteHotelById(Long id);
}