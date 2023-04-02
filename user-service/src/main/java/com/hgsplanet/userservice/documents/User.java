package com.hgsplanet.userservice.documents;

import com.hgsplanet.userservice.dto.UserDto;
import com.hgsplanet.userservice.enums.RelationWithUser;
import com.hgsplanet.userservice.enums.Role;
import com.hgsplanet.userservice.model.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Document(collection = "users")
public class User implements UserDetails {

    @Id
    private String accountId;
    private String username;
    private String email;
    private String password;
    private LocalDateTime creationTime;
    private String profileImgPath;
    private String phoneNum;
    private Role role;
    private Collection<Post> posts = new ArrayList<>();
    private Collection<Notification> notifications = new ArrayList<>();
    private Collection<Comment> comments = new ArrayList<>();
    private Collection<PostLike> postLikes = new ArrayList<>();
    private Map<String, RelationWithUser> visitedCities = new HashMap<>();


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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}


