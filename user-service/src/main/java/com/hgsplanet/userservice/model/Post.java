package com.hgsplanet.userservice.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Post {
    private String postId;
    private String postDescription;
    private LocalDateTime postDate;
    private String postImgPath;
}
