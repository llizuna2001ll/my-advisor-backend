package com.hgsplanet.userservice.web;

import com.hgsplanet.userservice.enums.RelationWithUser;
import com.hgsplanet.userservice.service.UserService;
import com.hgsplanet.userservice.dto.UserDto;
import com.hgsplanet.userservice.documents.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("api/v1/users")
public class UserRestController {

    private UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.findAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    ResponseEntity<User> getUserById(@PathVariable String id) {
        User user = userService.findUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }


    @PostMapping("/addUser")
    ResponseEntity<UserDto> addUser(@RequestBody UserDto user) {
        UserDto newUser = userService.addUser(user);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @PutMapping("/updateUser/{id}")
    ResponseEntity<UserDto> updateUser(@PathVariable String id, @RequestBody UserDto user) {
        UserDto updateUser = userService.updateUser(user);
        return new ResponseEntity<>(updateUser, HttpStatus.OK);
    }

    @Secured("ADMIN")
    @DeleteMapping("/deleteUser/{id}")
    ResponseEntity<UserDto> deleteUser(@PathVariable("id") String id) {
        userService.deleteUserById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/addCity")
    ResponseEntity<UserDto> addCity(@RequestParam String userId, @RequestParam String cityId, @RequestParam RelationWithUser relationWithUser) {
        userService.assignCity(userId, cityId, relationWithUser);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/removeCity")
    ResponseEntity<UserDto> removeCity(@RequestParam String userId, @RequestParam String cityId) {
        userService.removeCity(userId, cityId);
        return new ResponseEntity<>(HttpStatus.OK);
    }



}

