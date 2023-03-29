package com.hgsplanet.userservice.documents;

import com.hgsplanet.userservice.dto.UserDto;
import com.hgsplanet.userservice.model.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Document(collection = "users")
public class User {

    @Id
    private String accountId;
    private String username;
    private String email;
    private String password;
    private LocalDateTime creationTime;
    private String profileImgPath;
    private String phoneNum;
    private Collection<Post> posts = new ArrayList<>();
    private Collection<Notification> notifications = new ArrayList<>();
    private Collection<Comment> comments = new ArrayList<>();
    private Collection<PostLike> postLikes = new ArrayList<>();
    private Collection<City> visitedCities = new ArrayList<>();


    public static User toEntity(UserDto user){
        return User.builder()
                .accountId(user.getAccountId())
                .username(user.getUsername())
                .email(user.getEmail())
                .password(user.getPassword())
                .creationTime(user.getCreationTime())
                .profileImgPath(user.getProfileImgPath())
                .phoneNum(user.getPhoneNum())
                .build();
    }
}


