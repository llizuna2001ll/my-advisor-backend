package com.advisor.user.services;

import com.advisor.user.entities.User;
import com.advisor.user.repositories.UserRepository;
import com.advisor.user.entities.User;
import com.advisor.user.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public void addUser(User user){

        System.out.println("user added 2");
        userRepository.insert(user);
    }
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public Optional<User> getUserById(String id){

        return userRepository.findById(id);
    }

    public void updateUser(User user){
        User savedUser = userRepository.findById(user.getId()).orElseThrow(() -> new RuntimeException(
                String.format("cannot find user with id "+user.getId())
        ));
        savedUser.setUsername(user.getUsername());
        savedUser.setEmail(user.getEmail());
        savedUser.setPassword(user.getPassword());
        savedUser.setDateCreation(user.getDateCreation());
        userRepository.save(user);
    }
    public void deleteUser(String id){
        userRepository.deleteById(id);
    }

}
