package com.hgsplanet.postservice.service;

import com.hgsplanet.postservice.dao.PostRepository;
import com.hgsplanet.postservice.documents.Post;
import com.hgsplanet.postservice.dto.PostDto;
import com.hgsplanet.postservice.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Service
public class PostService {
    private final PostRepository postRepository;
    private final UserRestClient userRestClient;
    private final CommentRestClient commentRestClient;

    @Autowired
    public PostService(PostRepository postRepository, UserRestClient userRestClient, CommentRestClient commentRestClient) {
        this.postRepository = postRepository;
        this.userRestClient = userRestClient;
        this.commentRestClient = commentRestClient;
    }

    public PostDto addPost(PostDto post){
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJhdXRob3JpdGllcyI6W3siYXV0aG9yaXR5IjoiQlVTSU5FU1MifV0sInN1YiI6Iml6dW5hLWJ1c2luZXNzIiwiaWF0IjoxNjgzNTA4MDE0LCJleHAiOjE2ODM1MDk0NTR9.lsNqDl7kzsfEmnEnhsoRSmtZkk0hveaz6EJKk8GILqw";
        String authorization = "Bearer "+token;
        User user = userRestClient.findFullUserByUsernameForServices(post.getAccountUsername(), authorization);
        User business = userRestClient.findFullUserByUsernameForServices(post.getAccountUsername(), authorization);
        System.out.println(user.getUsername());
        System.out.println(business.getUsername());
        post.setPostDate(LocalDateTime.now());

        return PostDto.toDto(postRepository.save(Post.toEntity(post)));
    }

    public Post findPostById(String id){
        return postRepository.findById(id).orElseThrow(()-> new RuntimeException("Post Not Found"));
    }

    public List<Post> findAllPosts(){
        return postRepository.findAll();
    }

    public PostDto updatePost(PostDto post){
        return PostDto.toDto(postRepository.save(Post.toEntity(post)));
    }

    public void deletePostById(String id){
        postRepository.deleteById(id);
    }

    public Post getFullPost(String postId){
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJhdXRob3JpdGllcyI6W3siYXV0aG9yaXR5IjoiQlVTSU5FU1MifV0sInN1YiI6Iml6dW5hLWJ1c2luZXNzIiwiaWF0IjoxNjgzNTA4MDE0LCJleHAiOjE2ODM1MDk0NTR9.lsNqDl7kzsfEmnEnhsoRSmtZkk0hveaz6EJKk8GILqw";
        String authorization = "Bearer "+token;
        Post post = postRepository.findById(postId).get();
        post.setUser(userRestClient.findFullUserByUsernameForServices(post.getAccountUsername(), authorization));
        post.setComments(commentRestClient.getAllByPostId(postId));
        return post;
    }
}
