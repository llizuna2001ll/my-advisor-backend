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
public class Post{
    @Id
    private String postId;
    private String postTitle;
    private String postDescription;
    private Double postRating;
    private LocalDateTime postDate;
    private String postImgPath;
    private Collection<Comment> comments = new ArrayList<>();
    private Collection<PostLike> likes = new ArrayList<>();
    private String accountUsername;
    private String businessName;
    @Transient
    User user;


    public static Post toEntity(PostDto post){
        return Post.builder()
                .postId(post.getPostId())
                .postTitle(post.getPostTitle())
                .postDate(post.getPostDate())
                .postImgPath(post.getPostImgPath())
                .postDescription(post.getPostDescription())
                .accountUsername(post.getAccountUsername())
                .businessName(post.getBusinessName())
                .postRating(post.getPostRating())
                .build();
    }
}
