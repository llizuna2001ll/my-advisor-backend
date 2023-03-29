package com.hgsplanet.postservice.documents;

import com.hgsplanet.postservice.dto.PostDto;
import com.hgsplanet.postservice.model.Comment;
import com.hgsplanet.postservice.model.PostLike;
import com.hgsplanet.postservice.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Document(collection = "posts")
public class Post implements Serializable {
    @Id
    private String postId;
    private String postDescription;
    private LocalDateTime postDate;
    private String postImgPath;
    private Collection<Comment> comments = new ArrayList<>();
    private Collection<PostLike> postLikes = new ArrayList<>();
    private String accountId;
    @Transient
    private User user;

    public static Post toEntity(PostDto post){
        return Post.builder()
                .postId(post.getPostId())
                .postDate(post.getPostDate())
                .postImgPath(post.getPostImgPath())
                .postDescription(post.getPostDescription())
                .accountId(post.getAccountId())
                .build();
    }
}
