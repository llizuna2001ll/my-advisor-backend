package com.adviseme.postmicroservice.services;
import com.adviseme.postmicroservice.dto.PostDto;
import com.adviseme.postmicroservice.entitites.Post;
import com.adviseme.postmicroservice.models.User;
import com.adviseme.postmicroservice.repositories.PostRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PostService {
private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }
public PostDto addPost(PostDto postDto){
        return PostDto.Todo(postRepository.save(Post.ToEntity(postDto)));
    }

public Post getPostById(String id){
        return postRepository.findById(id).orElseThrow(()->new
                RuntimeException("post not found"));
}
public List<Post> findByUser(User user){
        return postRepository.findByUser(user);

}
public void deletePost(String id){
        postRepository.deleteById(id);
}

public PostDto updatePost(PostDto postDto){
    Post savedPost = postRepository.findById(postDto.getId()).orElseThrow(() ->
            new RuntimeException(
                    String.format("cannot find post with id "+postDto.getId())
            ));
    return PostDto.Todo(postRepository.save(savedPost));
}
}
