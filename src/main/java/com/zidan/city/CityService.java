package com.zidan.city;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CityService {
    @Autowired
	private CityRepository cityRepository;
	
	public List<City> getAllCity(){
		
		List<City> city = new ArrayList<City>();
		cityRepository.findAll().forEach(city::add);
		return city;
	}
	
	public List<City> getCityByGovName(String govName){
		return cityRepository.findAllByGovGovName(govName);
		
	}
	
	public Optional<City> getCityById(Integer cityId) {
		
		return cityRepository.findById(cityId);
	}
	

}
