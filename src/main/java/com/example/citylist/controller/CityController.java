package com.example.citylist.controller;

import com.example.citylist.common.UserConstant;
import com.example.citylist.dto.CityDto;
import com.example.citylist.model.City;
import com.example.citylist.service.CityService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.annotation.Secured;
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

@RestController
@RequestMapping("/cities")
public class CityController {

    @Autowired
    private CityService cityService;

    @Autowired
    private ModelMapper modelMapper;

    @CrossOrigin(origins = "http://localhost:4200")
   // @PreAuthorize("hasAuthority('ROLE_ALLOW_EDIT')")
    @GetMapping()
    public Page<CityDto> getCitiesPaginated(@RequestParam int offset, @RequestParam int pageSize) {
        Page<City> citiesPaginated = cityService.findCitiesWithPagination(offset, pageSize);

        Page<CityDto> citiesDtoPaginated = citiesPaginated.map(c -> convertToDto(c));

        return citiesDtoPaginated;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/search")
    public Page<CityDto> getCityByName(@RequestBody CityDto cityDto) {
        City city = convertToEntity(cityDto);
        Page<City> citiesPaginated = cityService.findCityByName(city.getName());

        Page<CityDto> citiesDtoPaginated = citiesPaginated.map(c -> convertToDto(c));

        return citiesDtoPaginated;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PreAuthorize("hasAuthority('ROLE_ALLOW_EDIT')")
    @PutMapping
    public CityDto updateCity(@RequestBody CityDto cityDto){
        City city = convertToEntity(cityDto);
        City updatedCity = cityService.updateCity(city);

        return convertToDto(updatedCity);
    }

    private City convertToEntity(CityDto cityDto){
        City city = modelMapper.map(cityDto, City.class);

        return city;
    }

    private CityDto convertToDto(City city){
        CityDto cityDto = modelMapper.map(city, CityDto.class);
        return cityDto;
    }
}
