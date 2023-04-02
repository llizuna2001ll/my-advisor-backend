package com.hgsplanet.postservice.service;

import com.hgsplanet.postservice.model.Comment;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;


@FeignClient(name ="COMMENT-SERVICE")
public interface CommentRestClient {
    @GetMapping(path = "api/v1/comments/post")
    public Collection<Comment> getAllByPostId(@RequestParam String postId);
}
