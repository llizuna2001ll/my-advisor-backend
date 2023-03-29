package com.hgsplanet.commentservice.dao;

import com.hgsplanet.commentservice.documents.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface CommentRepository extends MongoRepository<Comment, String> {
}
