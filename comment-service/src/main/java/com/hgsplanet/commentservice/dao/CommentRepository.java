package com.hgsplanet.commentservice.dao;

import com.hgsplanet.commentservice.documents.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface CommentRepository extends MongoRepository<Comment, String> {
    public List<Comment> findAllByPostId(String postId);
}
