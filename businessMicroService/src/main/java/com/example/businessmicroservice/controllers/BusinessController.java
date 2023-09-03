package com.example.businessmicroservice.controllers;

import com.example.businessmicroservice.dto.BusinessDto;
import com.example.businessmicroservice.entities.Business;
import com.example.businessmicroservice.repositories.BusinessRep;
import com.example.businessmicroservice.services.BusinessService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/business")
public class BusinessController {

    private final BusinessRep businessRep;
    private final BusinessService businessService;

    public BusinessController(BusinessRep businessRep, BusinessService businessService) {
        this.businessRep = businessRep;
        this.businessService = businessService;
    }
@PostMapping("/addBusiness")
    ResponseEntity<BusinessDto> addBusiness(@RequestBody BusinessDto business){
        BusinessDto businessDto = businessService.addBusiness(business);
        return new ResponseEntity<>(businessDto, HttpStatus.CREATED);
    }

@GetMapping
    ResponseEntity<List<Business>> getAllBusinesses(){
        List<Business> business = businessService.findAllBusinesses();
        return new ResponseEntity<>(business, HttpStatus.OK);
}

@GetMapping("/{id}")
    ResponseEntity<Business> getBusinessById(@PathVariable String id){
        Business business = businessService.findBusinessById(id);
        return new ResponseEntity<>(business, HttpStatus.OK);

}

@PutMapping("/update/{businessDto}")
    ResponseEntity<BusinessDto> updateBusiness(@RequestBody BusinessDto businessDto){
        BusinessDto businessDto1 = businessService.updateUser(businessDto);
        return new ResponseEntity<>(businessDto1, HttpStatus.OK);
}

@DeleteMapping("/delete/{id}")
    ResponseEntity<Business> deleteBusiness(@PathVariable String id){
        businessService.deleteBusiness(id);
        return new ResponseEntity<>(HttpStatus.OK);
}
}
