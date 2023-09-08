package com.hgsplanet.postservice.web;

import com.hgsplanet.postservice.dto.PostDto;
import com.hgsplanet.postservice.documents.Post;
import com.hgsplanet.postservice.model.PostLike;
import com.hgsplanet.postservice.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/posts")
public class PostRestController {

    private final PostService postService;

    public PostRestController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    ResponseEntity<List<Post>> getAllPosts(){
        List<Post> posts = postService.findAllPosts();
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    ResponseEntity<Post> getPostById(@PathVariable String id){
        Post post = postService.findPostById(id);
        return new ResponseEntity<>(post, HttpStatus.OK);
    }


    @PostMapping("/addPost")
    ResponseEntity<PostDto> addPost(@RequestBody PostDto post){
        PostDto newPost = postService.addPost(post);
        return new ResponseEntity<>(newPost, HttpStatus.CREATED);
    }

    @PutMapping("/updatePost/{id}")
    ResponseEntity<PostDto> updatePost(@PathVariable String id, @RequestBody PostDto post){
        PostDto updatePost = postService.updatePost(post);
        return new ResponseEntity<>(updatePost, HttpStatus.OK);
    }

    @DeleteMapping("/deletePost/{id}")
    ResponseEntity<PostDto> deletePost(@PathVariable("id") String id){
        postService.deletePostById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/getFullPost/{id}")
    ResponseEntity<Post> getFullPost(@PathVariable("id") String id){
        Post post = postService.getFullPost(id);
        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    @GetMapping("/byBusinessName/{businessName}")
    ResponseEntity<Collection<Post>> findByBusinessName(@PathVariable("businessName") String businessName){
        Collection<Post> posts = postService.findByBusinessName(businessName);
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @PostMapping("/likePost")
    ResponseEntity<Post> likePost(@RequestBody PostLike postLike){
        Post post = postService.likePost(postLike);
        return new ResponseEntity<>(post, HttpStatus.OK);
    }
    @PostMapping("/reportPost/{postId}")
    ResponseEntity<Post> reportPost(
            @PathVariable String postId,
            @RequestBody Map<String, String> requestBody
    ) {
        String reporter = requestBody.get("reporter");
        String reason = requestBody.get("reason");
        Post post = postService.reportPost(postId, reporter, reason);
        return new ResponseEntity<>(post, HttpStatus.OK);
    }
}
