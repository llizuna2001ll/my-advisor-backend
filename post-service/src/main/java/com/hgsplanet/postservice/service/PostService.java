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
        String authorization = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdXRob3JpdGllcyI6W3siYXV0aG9yaXR5IjoiVVNFUiJ9LHsiYXV0aG9yaXR5IjoiQURNSU4ifV0sInN1YiI6Iml6dW5hLXRlc3QyIiwiaWF0IjoxNjgzMTEwODgzLCJleHAiOjE2ODMxMTIzMjN9.MCsI8yIfuv6pVSSP2Hxxdvfs--3gwG3OW5qfIUHoJVw";
        User user = userRestClient.findUserByUsernameForServices(post.getAccountUsername(), authorization);
        User business = userRestClient.findUserByUsernameForServices(post.getAccountUsername(), authorization);
        post.setPostDate(LocalDateTime.now());
        Collection<PostDto> posts = user.getPosts();
        Collection<PostDto> businessPosts = user.getPostsAboutBusiness();
        businessPosts.add(post);
        posts.add(post);
        userRestClient.updateUserForServices(business, authorization);
        userRestClient.updateUserForServices(user, authorization);
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
        String authorization = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdXRob3JpdGllcyI6W3siYXV0aG9yaXR5IjoiVVNFUiJ9LHsiYXV0aG9yaXR5IjoiQURNSU4ifV0sInN1YiI6Iml6dW5hLXRlc3QyIiwiaWF0IjoxNjgzMTEwODgzLCJleHAiOjE2ODMxMTIzMjN9.MCsI8yIfuv6pVSSP2Hxxdvfs--3gwG3OW5qfIUHoJVw";
        Post post = postRepository.findById(postId).get();
        post.setUser(userRestClient.findUserByUsernameForServices(post.getAccountUsername(), authorization));
        post.setComments(commentRestClient.getAllByPostId(postId));
        return post;
    }
}
