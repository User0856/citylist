package com.example.citylist.repository;

import com.example.citylist.model.City;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository for access to "cities" table
 */

@Repository
public interface CityRepository extends JpaRepository<City, Integer> {

    Page<City> findAll(Pageable pageable);

    List<City> findCityByName(String name);

}
