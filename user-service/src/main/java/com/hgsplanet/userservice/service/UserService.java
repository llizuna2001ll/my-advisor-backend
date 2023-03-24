package com.hgsplanet.userservice.service;

import com.hgsplanet.userservice.dao.UserRepository;
import com.hgsplanet.userservice.dto.UserDto;
import com.hgsplanet.userservice.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
@Transactional
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDto addUser(UserDto user) {
        return UserDto.toDto(userRepository.save(User.toEntity(user)));
    }

    public User findUserById(String id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User Not Found"));
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public UserDto updateUser(UserDto user) {
        return UserDto.toDto(userRepository.save(User.toEntity(user)));
    }

    public void deleteUserById(String id) {
        userRepository.deleteById(id);
    }

}
