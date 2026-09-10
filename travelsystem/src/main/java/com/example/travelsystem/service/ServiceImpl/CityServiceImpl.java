package com.example.travelsystem.service.ServiceImpl;

import com.example.travelsystem.model.City;
import com.example.travelsystem.repository.CityRepository;
import com.example.travelsystem.service.CityService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityServiceImpl implements CityService {


    private final CityRepository cityRepository;


    public CityServiceImpl(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }


    @Override
    public City saveCity(City city) {
        return cityRepository.save(city);
    }


    @Override
    public List<City> getAllCities() {
        return cityRepository.findAll();
    }


    @Override
    public List<City> getAllCitiesWithCountry() {
        return cityRepository.findAllWithCountry();
    }


    @Override
    public City findCityById(Long id) {
        return cityRepository.findById(id).orElse(null);
    }


    @Override
    public City getCityById(Long id) {
        return cityRepository.findById(id).orElse(null);
    }


    @Override
    public City updateCity(City city) {
        return cityRepository.save(city);
    }


    @Override
    public void deleteCityById(Long id) {
        cityRepository.deleteById(id);
    }

}