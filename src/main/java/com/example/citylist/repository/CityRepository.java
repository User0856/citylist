package com.example.citylist.repository;

import com.example.citylist.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository for access to "cities" table
 */

@Repository()
public interface CityRepository extends JpaRepository<City, Long> {

    List<City> findCityByName(String name);

}
