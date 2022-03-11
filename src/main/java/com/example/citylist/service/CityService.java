package com.example.citylist.service;

import com.example.citylist.model.City;
import com.example.citylist.repository.CityRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service to get or modify cities, initially for basic CRUD operations
 */

@Service("CityService")
public class CityService {

    final CityRepository cityRepository;

    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public Page<City> findCitiesWithPagination(int offset, int pageSize){
        Page<City> cities = cityRepository.findAll(PageRequest.of(offset, pageSize));
        return cities;
    }

    public Page<City> findCityByName(String name){
        List<City> citiesByName = cityRepository.findCityByName(name);
        Page<City> cities = new PageImpl<>(citiesByName);
        return cities;
    }

    @Transactional
    public City updateCity(City city){
        Optional<City> cityFound = cityRepository.findById(city.getId());
        cityFound.get().setName(city.getName());
        cityFound.get().setImageURI(city.getImageURI());
        cityRepository.save(cityFound.get());
        return city;
    }
}
