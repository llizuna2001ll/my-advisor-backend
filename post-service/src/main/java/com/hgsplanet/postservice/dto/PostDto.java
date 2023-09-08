package com.hgsplanet.postservice.dto;

import com.hgsplanet.postservice.documents.Post;
import com.hgsplanet.postservice.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Builder
@AllArgsConstructor
@Data
public class PostDto implements Serializable {
    private String postId;
    private String postTitle;
    private String postDescription;
    private LocalDateTime postDate;
    private Double postRating;
    private String postImgPath;
    private String accountUsername;
    private String businessName;
    private Map<String, String> reports = new HashMap<>();

    public static PostDto toDto(Post post){
        return PostDto.builder()
                .postId(post.getPostId())
                .postTitle(post.getPostTitle())
                .postDate(post.getPostDate())
                .postImgPath(post.getPostImgPath())
                .postDescription(post.getPostDescription())
                .accountUsername(post.getAccountUsername())
                .businessName(post.getBusinessName())
                .postRating(post.getPostRating())
                .reports(post.getReports())
                .build();
    }
}
