package com.hgsplanet.commentservice.service;
import com.hgsplanet.commentservice.model.Post;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name = "POST-SERVICE")
public interface PostRestClient {
    @GetMapping(path = "api/v1/posts/{id}")
    public Post findPostById(@PathVariable String id);

}
