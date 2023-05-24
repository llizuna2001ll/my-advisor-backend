package com.hgsplanet.userservice.web;

import com.hgsplanet.userservice.enums.RelationWithUser;
import com.hgsplanet.userservice.model.Favorite;
import com.hgsplanet.userservice.model.PostLike;
import com.hgsplanet.userservice.service.UserService;
import com.hgsplanet.userservice.dto.UserDto;
import com.hgsplanet.userservice.documents.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
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
    ResponseEntity<UserDto> addCity(@RequestParam String username, @RequestParam String cityId, @RequestParam RelationWithUser relationWithUser) {
        userService.assignCity(username, cityId, relationWithUser);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/removeCity")
    ResponseEntity<UserDto> removeCity(@RequestParam String username, @RequestParam String cityId) {
        userService.removeCity(username, cityId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/assignRole/admin")
    ResponseEntity<UserDto> assignRole(@RequestParam String role, @RequestParam String username) {
        userService.assignRole(username, role);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/findBusinessByCity/{city}")
    ResponseEntity<Collection<User>> findBusinessByCity(@PathVariable String city) {
        Collection<User> users = userService.findBusinessByCity(city);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/businesses")
    ResponseEntity<Collection<User>> findBusinesses() {
        Collection<User> users = userService.findBusinesses();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/filterBusiness")
    ResponseEntity<Collection<User>> filterBusiness(@RequestParam Double rating, @RequestParam List<String> businessTypes, @RequestParam String city) {
        Collection<User> users = userService.filterBusiness(rating, businessTypes, city);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/topBusinesses")
    ResponseEntity<Collection<User>> findTopBusinesses() {
        Collection<User> users = userService.findTopBusinesses();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/findBusinessByBusinessType/{businessType}")
    ResponseEntity<Collection<User>> findBusinessByBusinessType(@PathVariable String businessType) {
        Collection<User> users = userService.findBusinessByBusinessType(businessType);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping("/changeRating")
    ResponseEntity<User> changeRating(@RequestParam Double rating, @RequestParam String businessName) {
        User user = userService.changeRating(businessName, rating);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/likePost")
    ResponseEntity<User> likePost(@RequestBody PostLike postLike){
        User user = userService.likePost(postLike);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/addFavorite")
    ResponseEntity<User> addFavorite(@RequestBody Favorite favorite){
        User user = userService.addFavorite(favorite);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/{username}/getFavorites")
    ResponseEntity<Collection<User>> findFavorites(@PathVariable String username) {
        Collection<User> users = userService.findFavorites(username);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
}

