package com.example.citylist.controller;

import com.example.citylist.dto.CityDto;
import com.example.citylist.model.City;
import com.example.citylist.service.CityService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
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

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/cities")
public class CityController {

    private final CityService cityService;

    private final ModelMapper modelMapper;

    public CityController(CityService cityService, ModelMapper modelMapper) {
        this.cityService = cityService;
        this.modelMapper = modelMapper;
    }

    @GetMapping()
    public Page<CityDto> getCitiesPaginated(@RequestParam int offset, @RequestParam int pageSize) {
        Page<City> citiesPaginated = cityService.findCitiesWithPagination(offset, pageSize);

        return citiesPaginated.map(this::convertToDto);
    }

    @PostMapping("/search")
    public Page<CityDto> getCityByName(@RequestBody CityDto cityDto) {
        City city = convertToEntity(cityDto);
        Page<City> citiesPaginated = cityService.findCityByName(city.getName());

        return citiesPaginated.map(this::convertToDto);
    }

    @PreAuthorize("hasAuthority('ROLE_ALLOW_EDIT')")
    @PutMapping
    public CityDto updateCity(@RequestBody CityDto cityDto){
        City city = convertToEntity(cityDto);
        City updatedCity = cityService.updateCity(city);

        return convertToDto(updatedCity);
    }

    private City convertToEntity(CityDto cityDto){

        return modelMapper.map(cityDto, City.class);
    }

    private CityDto convertToDto(City city){

        return modelMapper.map(city, CityDto.class);
    }
}
