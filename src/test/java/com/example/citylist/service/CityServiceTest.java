package com.example.citylist.service;

import com.example.citylist.model.City;
import com.example.citylist.repository.CityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
class CityServiceTest {

    private final Integer MOCK_CITY_ID = 1;
    private final String MOCK_CITY_NAME = "Tokyo";
    private final String MOCK_CITY_URI = "MockURI";

    @Autowired
    private CityService cityService;

    @MockBean
    private CityRepository cityRepository;

    private City testCity;
    private Page<City> mockCities;

    @BeforeEach
    public void setUp(){
        testCity = new City(MOCK_CITY_ID, MOCK_CITY_NAME, MOCK_CITY_URI);

        List<City> mockCitiesList = new ArrayList<>();
        mockCitiesList.add(testCity);
        mockCities = new PageImpl<>(mockCitiesList);

        when(cityRepository.findAll(PageRequest.of(0, 1))).thenReturn((mockCities));
        when(cityRepository.findCityByName(anyString())).thenReturn(Collections.singletonList(testCity));
        when(cityRepository.findById(anyInt())).thenReturn(Optional.of(testCity));
        when(cityRepository.save(any(City.class))).thenReturn(testCity);
    }

    @Test
    public void findCitiesTest(){
        Page<City> expected = mockCities;

        Page<City> result = cityService.findCities(Pageable.ofSize(1));

        assertEquals(result, expected);
    }

    @Test
    public void findCityByNameTest(){
        Page<City> expected = mockCities;

        Page<City> result = cityService.findCityByName(MOCK_CITY_NAME);

        assertEquals(result, expected);
    }

    @Test
    public void undateCityTest(){
        City expected = testCity;

        City result = cityService.updateCity(new City(MOCK_CITY_ID, MOCK_CITY_NAME, MOCK_CITY_URI));

        assertEquals(result, expected);
    }
}
