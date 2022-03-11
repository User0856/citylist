package com.example.citylist.controller;

import com.example.citylist.model.City;
import com.example.citylist.service.CityService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for cities CRUD operations
 * Provides paginated lists of cities
 */

@RestController
@RequestMapping("/cities")
public class CityController {

    final CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping()
    private Page<City> getCitiesPaginated(@RequestParam int offset, @RequestParam int pageSize) {
        Page<City> citiesPaginated = cityService.findCitiesWithPagination(offset, pageSize);
        return citiesPaginated;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/search")
    private Page<City> getCityByName(@RequestBody City city) {
        Page<City> citiesPaginated = cityService.findCityByName(city.getName());
        return citiesPaginated;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping
    private City updateCity(@RequestBody City city){
        City updatedCity = cityService.updateCity(city);
        return updatedCity;
    }
}
