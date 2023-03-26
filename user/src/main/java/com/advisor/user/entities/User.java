package com.advisor.user.entities;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.sql.Blob;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Document("users")

    public class User {
    @Id
    @Indexed(unique = true)
    private String id;
    @Field(name = "username")

    private String username;
    @Field(name = "email")

    private String email;
    @Field(name = "dateCreation")

    private Date dateCreation;
    @Field(name = "password")

    private String password;

}
