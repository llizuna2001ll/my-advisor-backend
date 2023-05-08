package com.hgsplanet.postservice.service;
import com.hgsplanet.postservice.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@FeignClient(name = "USER-SERVICE")
public interface UserRestClient {
    @GetMapping(path = "api/v1/users/services/{username}")
    public User findFullUserByUsernameForServices(@PathVariable String username, @RequestHeader("Authorization") String authorization);
    @PutMapping(path = "api/v1/users/services/updateUser")
    public User updateUserForServices(@RequestBody User user, @RequestHeader("Authorization") String authorization);

}
