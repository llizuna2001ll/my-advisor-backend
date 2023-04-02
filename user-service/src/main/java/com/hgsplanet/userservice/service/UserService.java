package com.hgsplanet.userservice.service;

import com.hgsplanet.userservice.dao.CityRepository;
import com.hgsplanet.userservice.dao.UserRepository;
import com.hgsplanet.userservice.documents.City;
import com.hgsplanet.userservice.dto.UserDto;
import com.hgsplanet.userservice.documents.User;
import com.hgsplanet.userservice.enums.RelationWithUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class UserService {
    private final UserRepository userRepository;
    private final CityRepository cityRepository;
    @Autowired
    public UserService(UserRepository userRepository, CityRepository cityRepository) {
        this.userRepository = userRepository;
        this.cityRepository = cityRepository;
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

    public void assignCity(String userId, String cityId, RelationWithUser relationWithUser){
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User Not Found"));
        Map<String, RelationWithUser> cities = user.getVisitedCities();
        City city = cityRepository.findById(cityId).orElseThrow(() -> new RuntimeException("City Not Found"));
        cities.put(city.getName(), relationWithUser);
        user.setVisitedCities(cities);
        userRepository.save(user);
    }

    public void removeCity(String userId, String cityId){
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User Not Found"));
        Map<String, RelationWithUser> cities = user.getVisitedCities();
        City city = cityRepository.findById(cityId).orElseThrow(() -> new RuntimeException("City Not Found"));
        cities.remove(city.getName());
        user.setVisitedCities(cities);
        userRepository.save(user);
    }
}
