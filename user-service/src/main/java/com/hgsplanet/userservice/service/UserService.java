package com.hgsplanet.userservice.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;
import com.hgsplanet.userservice.dao.CityRepository;
import com.hgsplanet.userservice.dao.UserRepository;
import com.hgsplanet.userservice.documents.City;
import com.hgsplanet.userservice.dto.BusinessDto;
import com.hgsplanet.userservice.dto.UserDto;
import com.hgsplanet.userservice.documents.User;
import com.hgsplanet.userservice.enums.RelationWithUser;
import com.hgsplanet.userservice.enums.Role;
import com.hgsplanet.userservice.model.AssignCityRequest;
import com.hgsplanet.userservice.model.Favorite;
import com.hgsplanet.userservice.model.PostLike;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Service
@Transactional
@Slf4j
public class UserService {
    private final UserRepository userRepository;
    private final CityRepository cityRepository;
    @Value("${application.bucket.name}")
    private String bucketName;
    private AmazonS3 s3Client;

    @Autowired
    public UserService(UserRepository userRepository, CityRepository cityRepository, AmazonS3 s3Client) {
        this.userRepository = userRepository;
        this.cityRepository = cityRepository;
        this.s3Client = s3Client;
    }

    public UserDto addUser(UserDto user) {
        User existingUser = userRepository.findByUsername(user.getUsername());
        if (existingUser != null) {
            throw new IllegalArgumentException("Username already taken");
        }
        return UserDto.toDto(userRepository.save(User.toEntity(user)));
    }

    public BusinessDto addBusiness(BusinessDto business) {
        User existingUser = userRepository.findByUsername(business.getUsername());
        if (existingUser != null) {
            throw new IllegalArgumentException("Username already taken");
        }
        return BusinessDto.toBusinessDto(userRepository.save(User.toBusiness(business)));
    }

    public User findUserById(String id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User Not Found"));
    }

    public UserDto findUserByUsername(String username) {
        try {
            User user = userRepository.findByUsername(username);
            UserDto userDto = UserDto.toDto(user);
            userDto.setAccountId(user.getAccountId());
            return userDto;
        } catch (RuntimeException e) {
            throw new RuntimeException("User Not Found");
        }
    }

    public User findFullUserByUsername(String username) {
        try {
            User user = userRepository.findByUsername(username);
            return user;
        } catch (RuntimeException e) {
            throw new RuntimeException("User Not Found");
        }
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public User updateUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUserByUsername(String username) {
        userRepository.deleteByUsername(username);
    }

    public void assignCity(String username, Collection<AssignCityRequest> assignCityRequests) {
        User user = userRepository.findByUsername(username);
        Map<String, RelationWithUser> cities = user.getVisitedCities();
        for (AssignCityRequest assignCityRequest : assignCityRequests) {
            try {
                City city = cityRepository.findById(assignCityRequest.getCityId()).orElseThrow(() -> new RuntimeException("City Not Found"));
                cities.put(city.getName(), assignCityRequest.getRelationWithUser());
                user.setVisitedCities(cities);
                userRepository.save(user);
            } catch (RuntimeException e) {
                throw new RuntimeException("User Not Found");
            }
        }
    }

    public void removeCity(String username, String cityId) {
        try {
            User user = userRepository.findByUsername(username);
            Map<String, RelationWithUser> cities = user.getVisitedCities();
            City city = cityRepository.findById(cityId).orElseThrow(() -> new RuntimeException("City Not Found"));
            cities.remove(city.getName());
            user.setVisitedCities(cities);
            userRepository.save(user);
        } catch (RuntimeException e) {
            throw new RuntimeException("User Not Found");
        }
    }

    public void assignRole(String username, String role) {
        try {
            User user = userRepository.findByUsername(username);
            Collection<Role> roles = user.getRoles();
            if (role.equals("admin"))
                roles.add(Role.ADMIN);
            else if (role.equals("business"))
                roles.add(Role.BUSINESS);
            user.setRoles(roles);
            userRepository.save(user);
        } catch (RuntimeException e) {
            throw new RuntimeException("User Not Found");
        }
    }

    public Collection<User> findBusinesses() {
        Collection<User> users = userRepository.findByRoles(Role.BUSINESS);
        return users;
    }

    public Collection<User> findBusinessByCity(String city) {
        Collection<User> users = userRepository.findByCityAndRoles(city, Role.BUSINESS);
        return users;
    }

    public Collection<User> findBusinessByBusinessType(String businessType) {
        Collection<User> users = userRepository.findByBusinessTypeAndRoles(businessType, Role.BUSINESS);
        return users;
    }

    public Collection<User> filterBusiness(Double rating, List<String> businessTypes, String city) {
        Collection<User> users = userRepository.filterBusiness(rating, businessTypes, city, Role.BUSINESS);
        return users;
    }

    public Collection<User> findTopBusinesses() {
        Collection<User> users = userRepository.findTop4ByRolesOrderByRatingDesc(Role.BUSINESS);
        return users;
    }

    public Collection<User> findBusinessesByUsername(String username){
        return userRepository.findByUsernameContainingAndRoles(username, Role.BUSINESS);
    }

    public User changeRating(String businessName, double rating) {
        double ratingSum = 0d;
        User business = userRepository.findByUsername(businessName);
        Collection<Double> ratings = business.getRatings();
        ratings.add(rating);
        business.setRatingCounter(business.getRatingCounter() + 1);

        for (Double tempRating : ratings) {
            if (tempRating != null) {
                ratingSum += tempRating;
            }
        }
        business.setRating(ratingSum / business.getRatingCounter());
        userRepository.save(business);
        return business;
    }

    public User likePost(PostLike postLike) {
        User user = findFullUserByUsername(postLike.getUsername());
        Collection<PostLike> likes = user.getLikes();
        if (likes.contains(postLike))
            likes.remove(postLike);
        else
            likes.add(postLike);
        return userRepository.save(user);
    }

    public User addFavorite(Favorite favorite) {
        User user = findFullUserByUsername(favorite.getAccountUsername());
        User business = findFullUserByUsername(favorite.getFavoriteUsername());
        Collection<String> fans = business.getFans();
        Collection<String> favorites = user.getFavorites();
        if (favorites.contains(favorite.getFavoriteUsername())) {
            favorites.remove(favorite.getFavoriteUsername());
            fans.remove(favorite.getAccountUsername());
        } else {
            favorites.add(favorite.getFavoriteUsername());
            fans.add(favorite.getAccountUsername());
        }
        userRepository.save(business);
        return userRepository.save(user);
    }

    public Collection<User> findFavorites(String username) {
        User user = findFullUserByUsername(username);
        Collection<String> favorites = user.getFavorites();
        Collection<User> businesses = new ArrayList<>();
        for (String favorite : favorites) {
            if (favorite != null) {
                businesses.add(findFullUserByUsername(favorite));
            }
        }
        return businesses;
    }

    public String uploadProfilePicture(MultipartFile file, String username) {
        File fileObj = convertMultiPartFileToFile(file);
        String fileName = "profilepictures/" + System.currentTimeMillis() + "_" + file.getOriginalFilename();
        s3Client.putObject(new PutObjectRequest(bucketName, fileName, fileObj));
        fileObj.delete();
        User user = userRepository.findByUsername(username);
        user.setProfileImgPath(fileName);
        userRepository.save(user);
        return "File uploaded : " + fileName;
    }

    public String deleteProfilePicture(String fileName) {
        s3Client.deleteObject(bucketName, "profilepictures/" +fileName);
        return fileName + " removed ...";
    }


    private File convertMultiPartFileToFile(MultipartFile file) {
        File convertedFile = new File(file.getOriginalFilename());
        try (FileOutputStream fos = new FileOutputStream(convertedFile)) {
            fos.write(file.getBytes());
        } catch (IOException e) {
            log.error("Error converting multipartFile to file", e);
        }
        return convertedFile;
    }
}
