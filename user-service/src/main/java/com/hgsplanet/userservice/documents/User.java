package com.hgsplanet.userservice.documents;

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

import javax.validation.constraints.*;
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

    @NotBlank(message = "username should not be empty")
    @Size(max = 20, min = 5)
    @Indexed(unique = true)
    private String username;

    @NotBlank(message = "email should not be empty")
    @Email(message = "Email not valid")
    private String email;

    @NotBlank(message = "password should not be empty")
    @Size(min = 6, max = 30)
    private String password;

    @NotNull
    private LocalDateTime creationTime;

    private String profileImgPath;

    @NotBlank(message = "phone number should not be empty")
    @Pattern(regexp = "^[0-9]*$", message = "Invalid phone number")
    private String phoneNum;


    //Exclusif for business users
    private String city;
    private String openingTime;
    private String closingTime;
    private String businessType;
    private Collection<Post> postsAboutBusiness = new ArrayList<>();


    private Collection<Role> roles;
    private Collection<Post> posts = new ArrayList<>();
    private Collection<Notification> notifications = new ArrayList<>();
    private Collection<Comment> comments = new ArrayList<>();
    private Collection<PostLike> postLikes = new ArrayList<>();
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
                .businessType(business.getBusinessType())
                .build();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.name()));
        }
        return authorities;
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


