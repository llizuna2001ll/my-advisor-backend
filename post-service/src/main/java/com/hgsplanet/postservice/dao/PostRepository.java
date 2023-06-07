package com.hgsplanet.postservice.dao;

import com.hgsplanet.postservice.documents.Post;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Collection;

@RepositoryRestResource

public interface PostRepository extends MongoRepository<Post, String> {
    Collection<Post> findByBusinessName(String businessName);
    Collection<Post> findByAccountUsername(String username);
}
