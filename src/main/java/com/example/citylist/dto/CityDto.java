package com.example.citylist.dto;

import com.example.citylist.model.City;

/**
 * DTO for @see {@link City}
 */

public class CityDto {

    private Long id;
    private String name;
    private String imageURI;

    public CityDto() {
    }

    public CityDto(Long id, String name, String imageURI) {
        this.id = id;
        this.name = name;
        this.imageURI = imageURI;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageURI() {
        return imageURI;
    }

    public void setImageURI(String imageURI) {
        this.imageURI = imageURI;
    }
}
