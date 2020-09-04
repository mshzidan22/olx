package com.zidan.city;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface CityRepository extends CrudRepository<City, Integer> {
public List<City> findByCityName(String cityName);
public List<City> findAllByGovGovName(String govName);

 }
