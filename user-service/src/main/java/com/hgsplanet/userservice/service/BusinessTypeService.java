package com.hgsplanet.userservice.service;

import com.hgsplanet.userservice.dao.BusinessTypeRepository;
import com.hgsplanet.userservice.documents.BusinessType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusinessTypeService {

    private final BusinessTypeRepository businessTypeRepository;

    @Autowired
    public BusinessTypeService(BusinessTypeRepository businessTypeRepository) {
        this.businessTypeRepository = businessTypeRepository;
    }

    public BusinessType addBusinessType(BusinessType businessType) {
        businessType.setBusinessCount(0);
        return businessTypeRepository.save(businessType);
    }

    public BusinessType findBusinessTypeByName(String name) {
        BusinessType businessType = businessTypeRepository.findBusinessTypeByTypeName(name);
        if (businessType == null) {
            throw new RuntimeException("BusinessType Not Found");
        }
        return businessType;
    }

    public List<BusinessType> findAllCities() {
        return businessTypeRepository.findAll();
    }

    public BusinessType updateBusinessType(BusinessType businessType) {
        return businessTypeRepository.save(businessType);
    }

    public void deleteBusinessTypeById(String id) {
        businessTypeRepository.deleteById(id);
    }

    public void increaseCount(String businessTypeName){
        BusinessType businessType = businessTypeRepository.findBusinessTypeByTypeName(businessTypeName);
        businessType.setBusinessCount(businessType.getBusinessCount()+1);
        businessTypeRepository.save(businessType);
    }

}
