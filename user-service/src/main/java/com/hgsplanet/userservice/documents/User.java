package com.hgsplanet.userservice.documents;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hgsplanet.userservice.dto.BusinessDto;
import com.hgsplanet.userservice.dto.UserDto;
import com.hgsplanet.userservice.enums.RelationWithUser;
import com.hgsplanet.userservice.enums.Role;
import com.hgsplanet.userservice.model.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
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
    @Indexed(unique = true)
    private String username;
    private String email;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    private LocalDateTime creationTime;
    private String profileImgPath;
    private String phoneNum;

    //Exclusif for businesses
    private String businessDescription;
    private String city;
    private String openingTime;
    private String closingTime;
    private String businessType;
    private Double rating;
    private Collection<Double> ratings;
    private int ratingCounter;
    private Collection<String> imgPaths = new ArrayList<>();


    private Collection<Role> roles;
    private Collection<Post> posts = new ArrayList<>();
    private Collection<PostLike> likes = new ArrayList<>();
    private Collection<String> favorites = new ArrayList<>();
    private Collection<String> fans = new ArrayList<>();
    private Collection<Notification> notifications = new ArrayList<>();
    private Collection<Comment> comments = new ArrayList<>();
    private Map<String, RelationWithUser> visitedCities = new HashMap<>();


    public static User toEntity(UserDto user){
        return User.builder()
                .username(user.getUsername())
                .email(user.getEmail())
                .password(user.getPassword())
                .roles(user.getRoles())
                .creationTime(user.getCreationTime())
                .profileImgPath(user.getProfileImgPath())
                .phoneNum(user.getPhoneNum())
                .visitedCities(user.getVisitedCities())
                .build();
    }

    public static User toBusiness(BusinessDto business){
        return User.builder()
                .username(business.getUsername())
                .email(business.getEmail())
                .password(business.getPassword())
                .roles(business.getRoles())
                .creationTime(business.getCreationTime())
                .profileImgPath(business.getProfileImgPath())
                .phoneNum(business.getPhoneNum())
                .openingTime(business.getOpeningTime())
                .closingTime(business.getClosingTime())
                .city(business.getCity())
                .businessType(business.getBusinessType())
                .build();
    }
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.name()));
        }
        return authorities;
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public boolean isEnabled() {
        return true;
    }
}
