package com.hgsplanet.userservice.dao;

import com.hgsplanet.userservice.documents.User;
import com.hgsplanet.userservice.enums.Role;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Collection;
import java.util.List;

@RepositoryRestResource
public interface UserRepository extends MongoRepository<User, String> {

    User findByUsername(String username);
    @Query("{rating: {$gte: ?0}, businessType: {$in: ?1}, city: ?2, roles: ?3}")
    Collection<User> filterBusiness(Double rating, List<String> businessTypes, String city, Role roles);
    Collection<User> findByCityAndRoles(String city, Role role);
    void deleteByUsername(String username);

    List<User> findTop4ByRolesOrderByRatingDesc(Role roles);
}
