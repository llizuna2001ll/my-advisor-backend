package com.hgsplanet.userservice.web;

import com.hgsplanet.userservice.enums.RelationWithUser;
import com.hgsplanet.userservice.enums.Role;
import com.hgsplanet.userservice.service.UserService;
import com.hgsplanet.userservice.dto.UserDto;
import com.hgsplanet.userservice.documents.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping("api/v1/users")
public class UserRestController {

    private final UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.findAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{username}")
    ResponseEntity<UserDto> getUserByUsername(@PathVariable String username) {
        UserDto user = userService.findUserByUsername(username);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/services/{username}")
    User getUserByUsernameForServices(@PathVariable String username) {
        User user = userService.findFullUserByUsername(username);
        return user;
    }

    @PostMapping("/addUser")
    ResponseEntity<?> addUser(@RequestBody UserDto user) {
        UserDto newUser = userService.addUser(user);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }


    @PutMapping("/updateUser")
    ResponseEntity<User> updateUser(@RequestBody User user) {
        User updateUser = userService.updateUser(user);
        return new ResponseEntity<>(updateUser, HttpStatus.OK);
    }

    @PutMapping("/services/updateUser")
        User updateUserForServices(@RequestBody User user) {
        User updateUser = userService.updateUser(user);
        return updateUser;
    }

    @DeleteMapping("/deleteUser/{username}")
    ResponseEntity<UserDto> deleteUser(@PathVariable("username") String username) {
        userService.deleteUserByUsername(username);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/addCity")
    ResponseEntity<UserDto> addCity(@RequestParam String username,@RequestParam String cityId, @RequestParam RelationWithUser relationWithUser) {
        userService.assignCity(username, cityId, relationWithUser);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/removeCity")
    ResponseEntity<UserDto> removeCity(@RequestParam String username, @RequestParam String cityId) {
        userService.removeCity(username, cityId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/assignRole")
    ResponseEntity<UserDto> assignRole(@RequestParam String role, @RequestParam String username) {
        userService.assignRole(username, role);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

