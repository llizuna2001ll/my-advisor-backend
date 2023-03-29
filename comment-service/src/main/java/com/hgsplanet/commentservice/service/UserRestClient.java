package com.hgsplanet.commentservice.service;

import com.hgsplanet.commentservice.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "USER-SERVICE")
public interface UserRestClient {
    @GetMapping(path = "api/v1/users/{id}")
    public User findUserById(@PathVariable String id);

    @PutMapping(path = "api/v1/updateUser/{id}")
    public User updateUser(@PathVariable String id, @RequestBody User user);
}
