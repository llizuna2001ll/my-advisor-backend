package com.hgsplanet.notificationservice.dao;

import com.hgsplanet.notificationservice.documents.Notification;
import com.hgsplanet.notificationservice.enums.NotificationType;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Collection;

@RepositoryRestResource
public interface NotificationRepository extends MongoRepository<Notification, String> {
    Collection<Notification> findAllByUserToAndNotificationType(String username, NotificationType notificationType);
}
