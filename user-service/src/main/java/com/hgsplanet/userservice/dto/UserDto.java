package com.hgsplanet.userservice.dto;

import com.hgsplanet.userservice.entities.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@Data
public class UserDto implements Serializable {
    private final String accountId;
    private final String username;
    private final String email;
    private final String password;
    private final LocalDateTime creationTime;
    private final String profileImgPath;
    private final String phoneNum;

    public static UserDto toDto(User user){
        return UserDto.builder()
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
