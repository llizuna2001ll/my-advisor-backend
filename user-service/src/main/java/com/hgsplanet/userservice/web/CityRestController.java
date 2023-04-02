package com.hgsplanet.userservice.web;

import com.hgsplanet.userservice.documents.City;
import com.hgsplanet.userservice.service.CityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("api/v1/cities")
public class CityRestController {

    private CityService userService;

    public CityRestController(CityService userService) {
        this.userService = userService;
    }

    @GetMapping
    ResponseEntity<List<City>> getAllCities() {
        List<City> cities = userService.findAllCities();
        return new ResponseEntity<>(cities, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    ResponseEntity<City> getCityById(@PathVariable String id) {
        City user = userService.findCityById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }


    @PostMapping("/addCity")
    ResponseEntity<City> addCity(@RequestBody City user) {
        City newCity = userService.addCity(user);
        return new ResponseEntity<>(newCity, HttpStatus.CREATED);
    }

    @PutMapping("/updateCity/{id}")
    ResponseEntity<City> updateCity(@PathVariable String id, @RequestBody City user) {
        City updateCity = userService.updateCity(user);
        return new ResponseEntity<>(updateCity, HttpStatus.OK);
    }

    @DeleteMapping("/deleteCity/{id}")
    ResponseEntity<City> deleteCity(@PathVariable("id") String id) {
        userService.deleteCityById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

