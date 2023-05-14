package com.hgsplanet.userservice.web;

import com.hgsplanet.userservice.documents.City;
import com.hgsplanet.userservice.service.CityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/v1/cities")
public class CityRestController {

    private CityService cityService;

    public CityRestController(CityService userService) {
        this.cityService = userService;
    }

    @GetMapping
    ResponseEntity<List<City>> getAllCities() {
        List<City> cities = cityService.findAllCities();
        return new ResponseEntity<>(cities, HttpStatus.OK);
    }

    @GetMapping("/{name}")
    ResponseEntity<City> getCityById(@PathVariable String name) {
        City user = cityService.findCityByName(name);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }


    @PostMapping("/addCity")
    ResponseEntity<City> addCity(@RequestBody City user) {
        City newCity = cityService.addCity(user);
        return new ResponseEntity<>(newCity, HttpStatus.CREATED);
    }

    @PutMapping("/updateCity/{id}")
    ResponseEntity<City> updateCity(@PathVariable String id, @RequestBody City user) {
        City updateCity = cityService.updateCity(user);
        return new ResponseEntity<>(updateCity, HttpStatus.OK);
    }

    @DeleteMapping("/deleteCity/{id}")
    ResponseEntity<City> deleteCity(@PathVariable("id") String id) {
        cityService.deleteCityById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}

