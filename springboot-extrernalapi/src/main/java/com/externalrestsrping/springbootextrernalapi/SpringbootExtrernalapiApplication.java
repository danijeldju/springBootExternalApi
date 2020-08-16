package com.externalrestsrping.springbootextrernalapi;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.externalrestsrping.springbootextrernalapi.service.CityService;

import com.externalrestsrping.springbootextrernalapi.domain.City;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootApplication
public class SpringbootExtrernalapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootExtrernalapiApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(CityService cityService) {
		return args -> {
			// read JSON and load json
			ObjectMapper mapper = new ObjectMapper();

			// call to write json
			mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
			TypeReference<List<City>> typeReference = new TypeReference<List<City>>() {
			};
			InputStream inputStream = TypeReference.class.getResourceAsStream("/json/result2.json");
			try {
				List<City> cities = mapper.readValue(inputStream, typeReference).subList(0, 10);

				boolean citieslist = Stream.of(cityService.findList()).anyMatch(it -> it == null || it.isEmpty());
				if (citieslist) {
					cityService.save(cities);
					System.out.println("Cities Saved!");
				} else {
					System.out.println("There is enough cities");
				}
			} catch (IOException e) {
				System.out.println("Unable to save cities: " + e.getMessage());
			}
		};
	}
}
