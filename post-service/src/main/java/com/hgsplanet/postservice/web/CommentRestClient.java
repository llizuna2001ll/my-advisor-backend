package com.hgsplanet.postservice.web;

import com.hgsplanet.postservice.model.Comment;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name ="COMMENT-SERVICE")
public interface CommentRestClient {
    @GetMapping(path = "api/v1/comments/post/{id}")
    public Comment findCommentByPostId(@PathVariable String id);
}
