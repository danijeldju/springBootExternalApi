package com.externalrestsrping.springbootextrernalapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.externalrestsrping.springbootextrernalapi.domain.City;

@Repository("cityRepository")
public interface CityRepository extends JpaRepository<City, Integer> {

}
