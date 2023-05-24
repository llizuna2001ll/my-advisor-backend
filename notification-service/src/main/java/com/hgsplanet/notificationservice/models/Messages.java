package com.hgsplanet.notificationservice.models;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Messages {
    private String sender;
    private LocalDateTime messageTime = LocalDateTime.now();
}
