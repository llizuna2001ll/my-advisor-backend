package com.hgsplanet.notificationservice.web;

import com.hgsplanet.notificationservice.models.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "USER-SERVICE")
public interface UserRestClient {
    @GetMapping(path = "api/v1/users/services/{username}")
    public User findFullUserByUsernameForServices(@PathVariable String username, @RequestHeader("Authorization") String authorization);
    @PutMapping(path = "api/v1/users/services/updateUser")
    public User updateUserForServices(@RequestBody User user, @RequestHeader("Authorization") String authorization);
}
