package com.hgsplanet.notificationservice.documents;

import com.hgsplanet.notificationservice.models.Messages;
import com.hgsplanet.notificationservice.models.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Collection;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Document(collection = "messages")
public class Conversation {
    @Id
    private String conversationId;
    private String userFrom;
    private String userTo;
    private Collection<Messages> messages = new ArrayList<>();
    @Transient
    private User userFromEntity;
}
