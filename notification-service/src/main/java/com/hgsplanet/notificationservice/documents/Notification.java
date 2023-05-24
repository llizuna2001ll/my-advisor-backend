package com.hgsplanet.notificationservice.documents;

import com.hgsplanet.notificationservice.enums.NotificationType;
import com.hgsplanet.notificationservice.models.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Document(collection = "notifications")
public class Notification {
    @Id
    private String notificationId;
    private String userFrom;
    private String userTo;
    private String postId;
    private String notificationObject;
    @Transient
    private User userFromEntity;
}
