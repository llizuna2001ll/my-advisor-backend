package com.hgsplanet.userservice.service;

import com.hgsplanet.userservice.dao.CityRepository;
import com.hgsplanet.userservice.documents.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class CityService {
    private final CityRepository cityRepository;

    @Autowired
    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public City addCity(City city) {
        return cityRepository.save(city);
    }

    public City findCityById(String id) {
        return cityRepository.findById(id).orElseThrow(() -> new RuntimeException("City Not Found"));
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

}
