package com.example.citylist.service;

import com.example.citylist.model.City;
import com.example.citylist.repository.CityRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service to get or modify cities, initially for basic CRUD operations
 */

@Service
public class CityService {

    private final CityRepository cityRepository;

    public CityService(CityRepository cityRepository) {

        this.cityRepository = cityRepository;
    }

    @Transactional
    public Page<City> findCities(Pageable pageable){

        return cityRepository.findAll(pageable);
    }

    @Transactional
    public Page<City> findCityByName(String name){
        List<City> citiesByName = cityRepository.findCityByName(name);

        return new PageImpl<>(citiesByName);
    }

    @Transactional
    public City updateCity(City city){

        City cityFound = cityRepository.findById(city.getId())
                .map(value -> {
                    value.setName(city.getName());
                    return value;
                })
                .map(value -> {
                    value.setImageURI(city.getImageURI());
                    return value;
                })
                .orElseThrow(() -> new IllegalArgumentException("No city with such parameters is present"));

        cityRepository.save(cityFound);

        return city;
    }
}
