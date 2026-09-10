
package com.example.travelsystem.service.ServiceImpl;
import com.example.travelsystem.model.Country;
import org.springframework.transaction.annotation.Transactional;
import com.example.travelsystem.repository.CountryRepository;
import com.example.travelsystem.service.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryServiceImpl implements CountryService {
    private final CountryRepository countryRepository;
    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }
    @Override
    @Transactional(readOnly = true)
    public Country getCountryById(Long id) {

        Country country =
                countryRepository.findById(id)
                        .orElse(null);


        if(country != null){
            country.getCities().size();
        }


        return country;
    }
    @Override
    public Country findById(Long id) {
        return countryRepository.findById(id).orElse(null);
    }

    @Override
    public Country saveCountry(Country country) {
        return countryRepository.save(country);
    }

    @Override
    public List<Country> getAllCountries() {
        return countryRepository.findAll();
    }

//    @Override
//    public Country getCountryById(Long id) {
//        return countryRepository.findById(id).orElse(null);
//    }

    @Override
    public Country updateCountry(Country country) {
        return countryRepository.save(country);
    }

    @Override
    public void deleteCountryById(Long id) {
        countryRepository.deleteById(id);
    }
}