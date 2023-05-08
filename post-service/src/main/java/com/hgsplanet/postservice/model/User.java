package com.hgsplanet.postservice.model;

import com.hgsplanet.postservice.dto.PostDto;
import lombok.Data;

import java.util.ArrayList;
import java.util.Collection;

@Data
public class User {
    private String accountId;
    private String username;
    private String profileImgPath;
}
