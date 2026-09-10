package com.example.travelsystem.service;

import com.example.travelsystem.model.City;

import java.util.List;

public interface CityService {

    City saveCity(City city);

    List<City> getAllCities();

    List<City> getAllCitiesWithCountry();

    City findCityById(Long id);

    City updateCity(City city);

    void deleteCityById(Long id);

    City getCityById(Long id);
}