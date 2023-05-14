package com.hgsplanet.userservice.dao;

import com.hgsplanet.userservice.documents.BusinessType;
import com.hgsplanet.userservice.documents.City;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface BusinessTypeRepository extends MongoRepository<BusinessType, String> {
    BusinessType findBusinessTypeByTypeName(String name);

}
