package com.example.citylist.controller;

import com.example.citylist.dto.APIResponse;
import com.example.citylist.model.City;
import com.example.citylist.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cities")
public class CityController {

    @Autowired
    CityService cityService;

    @GetMapping
    private APIResponse<List<City>> getCities() {
        List<City> allCities = cityService.findAllCities();
        return new APIResponse<>(allCities.size(), allCities);
    }

    @GetMapping("/paginated/{offset}/{pageSize}")
    private APIResponse<Page<City>> getCitiesPaginated(@PathVariable int offset, @PathVariable int pageSize) {
        Page<City> citiesPaginated = cityService.findProductsWithPagination(offset, pageSize);
        return new APIResponse<>(citiesPaginated.getSize(), citiesPaginated);
    }

    @PutMapping
    private City updateCity(@RequestBody City city){
        City updatedCity = cityService.updateCity(city);
        return updatedCity;
    }
}
