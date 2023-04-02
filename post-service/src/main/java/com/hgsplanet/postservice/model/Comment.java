package com.hgsplanet.postservice.model;

import lombok.Data;

@Data
public class Comment {
    private String commentId;
    private String accountId;
    private String commentText;
}
