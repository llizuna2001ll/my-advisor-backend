package com.hgsplanet.commentservice.service;

import com.hgsplanet.commentservice.dao.CommentRepository;
import com.hgsplanet.commentservice.documents.Comment;
import com.hgsplanet.commentservice.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final UserRestClient userRestClient;
    private final PostRestClient postRestClient;

    @Autowired
    public CommentService(CommentRepository commentRepository, UserRestClient userRestClient, PostRestClient postRestClient) {
        this.commentRepository = commentRepository;
        this.userRestClient = userRestClient;
        this.postRestClient = postRestClient;
    }

    public Comment addComment(Comment comment){
        comment.setCommentDate(LocalDateTime.now());
        return commentRepository.save(comment);
    }

    public Comment findCommentById(String id){
        return commentRepository.findById(id).orElseThrow(()-> new RuntimeException("Comment Not Found"));
    }

    public List<Comment> getAllByPostId(String id){
        return commentRepository.findAllByPostId(id);
    }

    public List<Comment> findAllComments(){
        return commentRepository.findAll();
    }

    public Comment updateComment(Comment comment){
        return commentRepository.save(comment);
    }

    public void deleteCommentById(String id){
        commentRepository.deleteById(id);
    }

    public Comment getFullComment(String commentId){
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJhdXRob3JpdGllcyI6W3siYXV0aG9yaXR5IjoiVVNFUiJ9XSwic3ViIjoiaXp1bmEtdGVzdDEiLCJpYXQiOjE2OTEyNDc5OTEsImV4cCI6MTY5MTg1Mjc5MX0.jZhAxZzob_6hQ6N0zdqA_4fjxMD0My0M6xAMqVoExXc";
        String authorization = "Bearer "+token;
        Comment comment = commentRepository.findById(commentId).get();
        comment.setUser(userRestClient.findFullUserByUsernameForServices(comment.getAccountUsername(), authorization));
        comment.setPost(postRestClient.findPostById(comment.getPostId()));
        return comment;
    }
}
