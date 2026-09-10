package com.example.travelsystem.repository;

import com.example.travelsystem.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CityRepository extends JpaRepository<City, Long> {


    @Query("""
           SELECT c 
           FROM City c
           JOIN FETCH c.country
           """)
    List<City> findAllWithCountry();

}