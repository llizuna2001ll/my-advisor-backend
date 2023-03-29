package com.example.businessmicroservice.entities;

import com.example.businessmicroservice.dto.BusinessDto;
import com.example.businessmicroservice.models.Comments;
import com.example.businessmicroservice.models.Posts;
import com.example.businessmicroservice.models.RecomDemandNotifications;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.ArrayList;
import java.util.List;

@Document("business")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Business {
    @Id
    @Indexed(unique = true)
    private String id;
    @Field(name = "type")
    private String type;
    @Field(name = "name")
    private String name;
    @Field(name = "description")
    private String description;
    @Field(name = "location")
    private String location;
    @Field(name = "phone")
    private String phone;
    @Field(name = "email")
    private String email;
    @Field(name = "registration")
    private String registration_number;
    @Field(name = "opening")
    private String opening_time;
    @Field(name = "closing")
    private String closing_time;
    private List<Posts> relatedPosts = new ArrayList<>();
    private List<Comments> relatedComments= new ArrayList<>();
    private List<RecomDemandNotifications> notifications= new ArrayList<>();


    public static Business ToEntity(BusinessDto business){
        return Business.builder()
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
