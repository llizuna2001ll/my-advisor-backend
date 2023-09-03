package com.adviseme.postmicroservice.entitites;

import com.adviseme.postmicroservice.dto.PostDto;
import com.adviseme.postmicroservice.models.Business;
import com.adviseme.postmicroservice.models.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Document("posts")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Post {
    @Id
    @Indexed(unique = true)
    private String id;
    @Field(name = "user")
    private User user;
    @Field(name = "business")
    private Business business;
    @Field(name = "content")
    private String content;
    @Field(name = "image")
    private String image;
    @Field(name = "writingDate")
    private String writingDate;

    public static Post ToEntity(PostDto post){
        return Post.builder()
                .id(post.getId())
                .writingDate(post.getWritingDate())
                .content(post.getContent())
                .image(post.getImage())
                .build();



    }

}
