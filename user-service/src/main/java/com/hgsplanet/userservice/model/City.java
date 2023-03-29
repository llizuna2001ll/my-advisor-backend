package com.hgsplanet.userservice.model;

import lombok.Data;

@Data
public class City {
    private Long cityId;
    private String name;
    private String relationWithUser;
}
