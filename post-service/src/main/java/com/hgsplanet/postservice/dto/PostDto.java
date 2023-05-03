package com.hgsplanet.postservice.dto;

import com.hgsplanet.postservice.documents.Post;
import com.hgsplanet.postservice.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@Data
public class PostDto implements Serializable {
    private String postId;
    private String postDescription;
    private LocalDateTime postDate;
    private String postImgPath;
    private String accountUsername;
    private String businessName;

    public static PostDto toDto(Post post){
        return PostDto.builder()
                .postId(post.getPostId())
                .postDate(post.getPostDate())
                .postImgPath(post.getPostImgPath())
                .postDescription(post.getPostDescription())
                .accountUsername(post.getAccountUsername())
                .businessName(post.getBusinessName())
                .build();
    }
}
