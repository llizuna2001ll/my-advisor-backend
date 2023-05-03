package com.hgsplanet.userservice.dto;

import com.hgsplanet.userservice.documents.User;
import com.hgsplanet.userservice.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Collection;

@Builder
@AllArgsConstructor
@Data
public class BusinessDto {

    private String username;
    private String email;
    private String password;
    private String profileImgPath;
    private String phoneNum;
    private LocalDateTime creationTime;
    private Collection<Role> roles;
    private String city;
    private String openingTime;
    private String closingTime;
    private String businessType;

    public static BusinessDto toBusinessDto(User business){
        return BusinessDto.builder()
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
}
