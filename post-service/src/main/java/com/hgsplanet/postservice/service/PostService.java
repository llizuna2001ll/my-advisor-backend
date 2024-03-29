package com.hgsplanet.postservice.service;

import com.hgsplanet.postservice.dao.PostRepository;
import com.hgsplanet.postservice.documents.Post;
import com.hgsplanet.postservice.dto.PostDto;
import com.hgsplanet.postservice.model.PostLike;
import com.hgsplanet.postservice.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class PostService {
    private final PostRepository postRepository;
    private final UserRestClient userRestClient;
    private final CommentRestClient commentRestClient;
    private final String token = "eyJhbGciOiJIUzI1NiJ9.eyJhdXRob3JpdGllcyI6W3siYXV0aG9yaXR5IjoiVVNFUiJ9XSwic3ViIjoiaXp1bmEtdGVzdCIsImlhdCI6MTY5Mzc3MjI1NSwiZXhwIjoxNjk0Mzc3MDU1fQ.zrCa07tgrgBFxqyiSzUuCwaFUHjCGEWecvJGG3KVQXo";

    @Autowired
    public PostService(PostRepository postRepository, UserRestClient userRestClient, CommentRestClient commentRestClient) {
        this.postRepository = postRepository;

        this.userRestClient = userRestClient;
        this.commentRestClient = commentRestClient;
    }

    public PostDto addPost(PostDto post){
        String authorization = "Bearer "+token;
        User user = userRestClient.findFullUserByUsernameForServices(post.getAccountUsername(), authorization);
        User business = userRestClient.findFullUserByUsernameForServices(post.getAccountUsername(), authorization);
        System.out.println(user.getUsername());
        System.out.println(business.getUsername());
        post.setPostDate(LocalDateTime.now());
        return PostDto.toDto(postRepository.save(Post.toEntity(post)));
    }

    public Post findPostById(String id){
        return postRepository.findById(id).orElseThrow(()-> new RuntimeException("Post Not Found"+id));
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
        String authorization = "Bearer "+token;
        Post post = postRepository.findById(postId).get();
        post.setUser(userRestClient.findFullUserByUsernameForServices(post.getAccountUsername(), authorization));
        post.setComments(commentRestClient.getAllByPostId(postId));
        return post;
    }

    public Collection<Post> findByBusinessName(String businessName){
        String authorization = "Bearer "+token;
        Collection<Post> posts = postRepository.findByBusinessName(businessName);
        Collection<Post> newPosts = new ArrayList<>();
        for (Post post: posts) {
            post.setUser(userRestClient.findFullUserByUsernameForServices(post.getAccountUsername(), authorization));
            post.setComments(commentRestClient.getAllByPostId(post.getPostId()));
            newPosts.add(post);
        }
        return newPosts;
    }

    public Post likePost(PostLike postLike){
        Post post = findPostById(postLike.getPostId());
        Collection<PostLike> likes = post.getLikes();
        if(likes.contains(postLike))
            likes.remove(postLike);
        else
            likes.add(postLike);
        return postRepository.save(post);
    }

    public Post reportPost(String postId, String reporter, String reason){
        Post post = findPostById(postId);
        Map<String, String> reports = post.getReports();
        reports.put(reporter, reason);
        post.setReports(reports);
        return postRepository.save(post);
    }

}
