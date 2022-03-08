package com.example.citylist.service;

import com.example.citylist.model.City;
import com.example.citylist.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CityService {

    @Autowired
    CityRepository cityRepository;

    public List<City> findAllCities(){
        return cityRepository.findAll();
    }

    public Page<City> findProductsWithPagination(int offset, int pageSize){
        Page<City> cities = cityRepository.findAll(PageRequest.of(offset, pageSize));
        return cities;
    }

    public City updateCity(City city){
        Optional<City> cityFound = cityRepository.findById(city.getId());
        cityFound.get().setName(city.getName());
        cityFound.get().setImageURI(city.getImageURI());
        cityRepository.save(cityFound.get());
        return city;
    }
}
