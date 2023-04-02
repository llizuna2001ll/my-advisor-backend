package com.hgsplanet.userservice.dao;

import com.hgsplanet.userservice.documents.City;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Collection;
import java.util.List;

public interface CityRepository extends MongoRepository<City, String> {

    public Collection<City> findAllByCityId(String cityId);
}
