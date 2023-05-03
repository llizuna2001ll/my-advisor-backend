package com.hgsplanet.userservice.dto;

import com.hgsplanet.userservice.documents.User;
import com.hgsplanet.userservice.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.data.mongodb.core.index.Indexed;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;

@Builder
@AllArgsConstructor
@Data
public class UserDto implements Serializable {
    private String accountId;
    private String username;
    private String email;
    private String password;
    private String profileImgPath;
    private String phoneNum;
    private LocalDateTime creationTime;
    private Collection<Role> roles;

    public static UserDto toDto(User user){
        return UserDto.builder()
                .username(user.getUsername())
                .email(user.getEmail())
                .password(user.getPassword())
                .roles(user.getRoles())
                .creationTime(user.getCreationTime())
                .profileImgPath(user.getProfileImgPath())
                .phoneNum(user.getPhoneNum())
                .build();
    }
}
