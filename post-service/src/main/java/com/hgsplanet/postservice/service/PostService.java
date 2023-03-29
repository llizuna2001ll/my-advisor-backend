package com.hgsplanet.postservice.service;

import com.hgsplanet.postservice.dao.PostRepository;
import com.hgsplanet.postservice.documents.Post;
import com.hgsplanet.postservice.dto.PostDto;
import com.hgsplanet.postservice.model.User;
import com.hgsplanet.postservice.web.UserRestClient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    private final PostRepository postRepository;
    private final UserRestClient userRestClient;

    public PostService(PostRepository postRepository, UserRestClient userRestClient) {
        this.postRepository = postRepository;
        this.userRestClient = userRestClient;
    }

    public PostDto addPost(PostDto post){
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
        Post post = postRepository.findById(postId).get();
        post.setUser(userRestClient.findUserById(post.getAccountId()));
        return post;
    }
}
