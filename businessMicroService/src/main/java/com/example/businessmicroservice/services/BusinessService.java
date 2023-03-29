package com.example.businessmicroservice.services;

import com.example.businessmicroservice.dto.BusinessDto;
import com.example.businessmicroservice.entities.Business;
import com.example.businessmicroservice.repositories.BusinessRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class BusinessService {
    private final BusinessRep businessRep;
@Autowired
    public BusinessService(BusinessRep businessRep) {
        this.businessRep = businessRep;
    }


    public BusinessDto addBusiness(BusinessDto businessDto){
    return BusinessDto.ToDo(businessRep.save(Business.ToEntity(businessDto)));
    }

    public Business findBusinessById(String id){
    return businessRep.findById(id).orElseThrow(()-> new RuntimeException("business not found"));
    }
    public List<Business> findAllBusinesses(){
    return businessRep.findAll();
    }
    public BusinessDto updateUser(BusinessDto businessDto){


        Business savedBusiness = businessRep.findById(businessDto.getId()).orElseThrow(() ->
                new RuntimeException(
                String.format("cannot find user with id "+businessDto.getId())
        ));
        return BusinessDto.ToDo(businessRep.save(savedBusiness));

    }
    public void deleteBusiness(String id){
        businessRep.deleteById(id);
    }
}
