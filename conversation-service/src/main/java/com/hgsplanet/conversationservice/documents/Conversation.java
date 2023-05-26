package com.hgsplanet.conversationservice.documents;


import com.hgsplanet.conversationservice.models.Message;
import com.hgsplanet.conversationservice.models.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Document(collection = "conversations")
public class Conversation {
    @Id
    private String conversationId;
    @Indexed
    private String userFrom;
    @Indexed
    private String username;
    private Collection<Message> messages = new ArrayList<>();
    private LocalDateTime lastMessageTime;
    @Transient
    private User userFromEntity;
}
