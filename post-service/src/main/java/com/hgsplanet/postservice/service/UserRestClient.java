package com.hgsplanet.postservice.service;
import com.hgsplanet.postservice.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name = "USER-SERVICE")
public interface UserRestClient {
    @GetMapping(path = "api/v1/users/{id}")
    public User findUserById(@PathVariable String id);

}
