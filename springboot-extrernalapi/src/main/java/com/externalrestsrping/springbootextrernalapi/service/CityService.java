package com.externalrestsrping.springbootextrernalapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.externalrestsrping.springbootextrernalapi.domain.City;
import com.externalrestsrping.springbootextrernalapi.repository.CityRepository;

@Service("cityService")
public class CityService {

	@Autowired
	private CityRepository cityRepository;

	public CityService(CityRepository cityRepository) {
		this.cityRepository = cityRepository;
	}

	public List<City> findList() {
		return cityRepository.findAll();
	}

	public List<City> getAll() {
		return cityRepository.findAll();
	}

	public List<City> list() {
		return cityRepository.findAll();
	}

	public void save(List<City> cities) {
		cityRepository.saveAll(cities);
	}

	public City save(City city) {

		return cityRepository.save(city);
	}

	public City save(City city, String name) {

		city.setName(name);
		return cityRepository.save(city);
	}

	public void delete(Integer id) {
		cityRepository.deleteById(id);
	}

	public City get(Integer id) {
		return cityRepository.findById(id).get();

	}

}
