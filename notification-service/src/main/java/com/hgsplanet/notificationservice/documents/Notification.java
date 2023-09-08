package com.hgsplanet.notificationservice.documents;

import com.hgsplanet.notificationservice.enums.NotificationType;
import com.hgsplanet.notificationservice.models.User;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
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
    @Indexed
    private String username;
    private String postId;
    private String notificationObject;
    @Builder.Default
    private Boolean seen = false;
}
