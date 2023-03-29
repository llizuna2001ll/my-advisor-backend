package com.adviseme.postmicroservice.controllers;

import com.adviseme.postmicroservice.dto.PostDto;
import com.adviseme.postmicroservice.entitites.Post;
import com.adviseme.postmicroservice.models.User;
import com.adviseme.postmicroservice.repositories.PostRepository;
import com.adviseme.postmicroservice.services.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/posts")

public class PostController {
private final PostRepository postRepository;
private final PostService postService;

    public PostController(PostRepository postRepository, PostService postService) {
        this.postRepository = postRepository;
        this.postService = postService;
    }
    @PostMapping("/addPost")
    ResponseEntity<PostDto> addBusiness(@RequestBody PostDto post){
        PostDto postDto = postService.addPost(post);
        return new ResponseEntity<>(postDto, HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    ResponseEntity<Post> getPostById(@PathVariable String id){
        Post post = postService.getPostById(id);
        return new ResponseEntity<>(post, HttpStatus.OK);

    }
    @GetMapping("/find")
    ResponseEntity <List<Post>> findByUser(@RequestBody User user){
        List<Post> post = postService.findByUser(user);
        return new ResponseEntity<>(post, HttpStatus.OK);

    }
    @PutMapping("/update/{id}")
    ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto){
        PostDto postDto1 = postService.updatePost(postDto);
        return new ResponseEntity<>(postDto1, HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    ResponseEntity<Post> deletePost(@PathVariable String id){
        postService.deletePost(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
