package com.hgsplanet.userservice.service;

import com.hgsplanet.userservice.dao.CityRepository;
import com.hgsplanet.userservice.documents.City;
import com.mongodb.client.model.Collation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service
@Transactional
public class CityService {
    private final CityRepository cityRepository;
    private final UserService userService;
    @Autowired
    public CityService(CityRepository cityRepository, UserService userService) {
        this.cityRepository = cityRepository;
        this.userService = userService;
    }

    public City addCity(City city) {
        city.setBusinessCount(0);
        return cityRepository.save(city);
    }

    public City findCityByName(String name) {
        City city = cityRepository.findCityByName(name);
        if (city == null) {
            throw new RuntimeException("City Not Found");
        }
        return city;
    }

    public List<City> findAllCities() {
        return cityRepository.findAll();
    }

    public City updateCity(City city) {
        return cityRepository.save(city);
    }

    public void deleteCityById(String id) {
        cityRepository.deleteById(id);
    }

    public void increaseCount(String cityName){
        City city = cityRepository.findCityByName(cityName);
        city.setBusinessCount(city.getBusinessCount()+1);
        cityRepository.save(city);
    }
}
