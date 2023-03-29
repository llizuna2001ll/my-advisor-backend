package com.example.businessmicroservice.dto;

import com.example.businessmicroservice.entities.Business;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BusinessDto implements Serializable {
    private String id;
    private String type;
    private String name;
    private String description;
    private String location;
    private String phone;
    private String email;
    private String registration_number;
    private String opening_time;
    private String closing_time;

    public static BusinessDto ToDo(Business business){
        return BusinessDto.builder()
                .id(business.getId())
                .type(business.getType())
                .name(business.getName())
                .description(business.getDescription())
                .location(business.getLocation())
                .phone(business.getPhone())
                .email(business.getEmail())
                .registration_number(business.getRegistration_number())
                .opening_time(business.getOpening_time())
                .closing_time(business.getClosing_time())
                .build();
    }
}
