package com.hgsplanet.commentservice.service;

import com.hgsplanet.commentservice.dao.CommentRepository;
import com.hgsplanet.commentservice.documents.Comment;
import com.hgsplanet.commentservice.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return commentRepository.save(comment);
    }

    public Comment findCommentById(String id){
        return commentRepository.findById(id).orElseThrow(()-> new RuntimeException("Comment Not Found"));
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
        Comment comment = commentRepository.findById(commentId).get();
        comment.setUser(userRestClient.findUserById(comment.getAccountId()));
        comment.setPost(postRestClient.findPostById(comment.getPostId()));
        return comment;
    }
}
