package com.adviseme.postmicroservice.dto;

import com.adviseme.postmicroservice.entitites.Post;
import com.adviseme.postmicroservice.models.Business;
import com.adviseme.postmicroservice.models.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostDto implements Serializable {
    private String id;
    private String content;
    private String image;
    private String writingDate;

public static PostDto Todo(Post post){
    return PostDto.builder()
            .id(post.getId())
            .content(post.getContent())
            .writingDate(post.getWritingDate())
            .image(post.getImage())
            .build();
}
}
