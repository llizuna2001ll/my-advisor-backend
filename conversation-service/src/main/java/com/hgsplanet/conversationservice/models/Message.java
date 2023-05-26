package com.hgsplanet.conversationservice.models;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Message {
    private String sender;
    private String message;
    private LocalDateTime messageTime = LocalDateTime.now();
}
