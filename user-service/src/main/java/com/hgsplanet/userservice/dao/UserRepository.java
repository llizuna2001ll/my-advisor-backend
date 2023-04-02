package com.hgsplanet.userservice.dao;

import com.hgsplanet.userservice.documents.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource
public interface UserRepository extends MongoRepository<User, String> {

    User findByUsername(String username);
}
