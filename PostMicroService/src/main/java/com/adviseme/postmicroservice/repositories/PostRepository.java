package com.adviseme.postmicroservice.repositories;

import com.adviseme.postmicroservice.entitites.Post;
import com.adviseme.postmicroservice.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {
    List<Post> findByUser(User user);
}
