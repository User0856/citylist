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

@Service()
public class CityService {

    private final CityRepository cityRepository;

    public CityService(CityRepository cityRepository) {

        this.cityRepository = cityRepository;
    }

    public Page<City> findCitiesWithPagination(int offset, int pageSize){

        return cityRepository.findAll(PageRequest.of(offset, pageSize));
    }

    public Page<City> findCityByName(String name){
        List<City> citiesByName = cityRepository.findCityByName(name);

        return new PageImpl<>(citiesByName);
    }

    @Transactional
    public City updateCity(City city){
        Optional<City> cityFound = cityRepository.findById(city.getId());
        cityFound.ifPresent(value -> value.setName(city.getName()));
        cityFound.ifPresent(value -> value.setImageURI(city.getImageURI()));

        assert cityFound.orElse(null) != null;

        cityRepository.save(cityFound.orElse(null));

        return city;
    }
}
