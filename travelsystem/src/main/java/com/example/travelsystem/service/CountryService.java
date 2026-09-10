package com.example.travelsystem.service;

import com.example.travelsystem.model.Country;

import java.util.List;

public interface CountryService {
    Country saveCountry(Country country);
    List<Country> getAllCountries();
    Country getCountryById(Long id);
    Country updateCountry(Country country);
    void deleteCountryById(Long id);
    Country findById(Long id);
}
