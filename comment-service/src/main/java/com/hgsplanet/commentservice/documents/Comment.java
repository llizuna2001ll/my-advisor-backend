package com.hgsplanet.commentservice.documents;

import com.hgsplanet.commentservice.model.Post;
import com.hgsplanet.commentservice.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Document(collection = "comments")
public class Comment {
    @Id
    private String commentId;
    private String commentText;
    private LocalDateTime commentDate;
    private String accountUsername;
    private String postId;
    @Transient
    private Post post;
    @Transient
    private User user;
}

