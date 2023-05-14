package com.hgsplanet.userservice.web;

import com.hgsplanet.userservice.documents.BusinessType;
import com.hgsplanet.userservice.service.BusinessTypeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/v1/businessTypes")
public class BusinessTypeRestController {

    private BusinessTypeService businessTypeService;

    public BusinessTypeRestController(BusinessTypeService userService) {
        this.businessTypeService = userService;
    }

    @GetMapping
    ResponseEntity<List<BusinessType>> getAllCities() {
        List<BusinessType> businessTypes = businessTypeService.findAllCities();
        return new ResponseEntity<>(businessTypes, HttpStatus.OK);
    }

    @GetMapping("/{name}")
    ResponseEntity<BusinessType> getBusinessTypeById(@PathVariable String name) {
        BusinessType user = businessTypeService.findBusinessTypeByName(name);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }


    @PostMapping("/addBusinessType")
    ResponseEntity<BusinessType> addBusinessType(@RequestBody BusinessType user) {
        BusinessType newBusinessType = businessTypeService.addBusinessType(user);
        return new ResponseEntity<>(newBusinessType, HttpStatus.CREATED);
    }

    @PutMapping("/updateBusinessType")
    ResponseEntity<BusinessType> updateBusinessType(@RequestBody BusinessType user) {
        BusinessType updateBusinessType = businessTypeService.updateBusinessType(user);
        return new ResponseEntity<>(updateBusinessType, HttpStatus.OK);
    }

    @DeleteMapping("/deleteBusinessType/{id}")
    ResponseEntity<BusinessType> deleteBusinessType(@PathVariable("id") String id) {
        businessTypeService.deleteBusinessTypeById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

