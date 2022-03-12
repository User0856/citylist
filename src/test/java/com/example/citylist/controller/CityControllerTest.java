package com.example.citylist.controller;

import com.example.citylist.model.City;
import com.example.citylist.service.CityService;
import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CityControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CityService cityService;

    private City testCity;

    @BeforeEach
    public void setUp(){
        testCity = new City(1L, "Tokyo", "MockURI");

        List<City> mockCitiesList = new ArrayList<>();
        mockCitiesList.add(testCity);
        final Page<City> mockCities = new PageImpl<>(mockCitiesList);

        when(cityService.findCitiesWithPagination(anyInt(), anyInt())).thenReturn(mockCities);
        when(cityService.updateCity(any(City.class))).thenReturn(testCity);
        when(cityService.findCityByName(anyString())).thenReturn(mockCities);
    }

    @Test
    @WithMockUser(username="admin", roles={"ALLOW_EDIT"})
    public void getCitiesPaginatedTest_vaid() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/cities")
                        .param("offset", "0")
                        .param("pageSize", "10")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").isArray())
                .andExpect(jsonPath("$.content", hasSize(1)))
                .andExpect(jsonPath("$.content[0].id").value("1"))
                .andExpect(jsonPath("$.content[0].name").value("Tokyo"))
                .andExpect(jsonPath("$.content[0].imageURI").value("MockURI"));
    }

    @Test
    @WithMockUser(username="admin", roles={"ALLOW_EDIT"})
    public void getCityByNameTest_valid() throws Exception {

        Gson gson = new Gson();
        String json = gson.toJson(testCity);

        mockMvc.perform(MockMvcRequestBuilders.put("/cities")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.name").value("Tokyo"))
                .andExpect(jsonPath("$.imageURI").value("MockURI"));
    }

    @Test
    @WithMockUser(username="admin", roles={"ALLOW_EDIT"})
    public void updateCitiesTest_valid() throws Exception {

        Gson gson = new Gson();
        String json = gson.toJson(testCity);

        mockMvc.perform(MockMvcRequestBuilders.post("/cities/search")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").isArray())
                .andExpect(jsonPath("$.content", hasSize(1)))
                .andExpect(jsonPath("$.content[0].id").value("1"))
                .andExpect(jsonPath("$.content[0].name").value("Tokyo"))
                .andExpect(jsonPath("$.content[0].imageURI").value("MockURI"));
    }
}
