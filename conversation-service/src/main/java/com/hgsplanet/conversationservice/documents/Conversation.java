package com.hgsplanet.conversationservice.documents;


import com.hgsplanet.conversationservice.models.Message;
import com.hgsplanet.conversationservice.models.User;
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
@Document(collection = "conversations")
public class Conversation {
    @Id
    private String conversationId;
    private String userFrom;
    private String userTo;
    private Collection<Message> messages = new ArrayList<>();
    @Transient
    private User userFromEntity;
}
