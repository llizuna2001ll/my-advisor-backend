package com.example.businessmicroservice.repositories;

import com.example.businessmicroservice.entities.Business;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BusinessRep extends MongoRepository<Business, String> {
}
