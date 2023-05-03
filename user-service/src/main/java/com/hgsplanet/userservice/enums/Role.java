package com.hgsplanet.userservice.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Collection;

public enum Role {
    @JsonProperty("business")
    BUSINESS,
    @JsonProperty("admin")
    ADMIN,
    @JsonProperty("user")
    USER
}
