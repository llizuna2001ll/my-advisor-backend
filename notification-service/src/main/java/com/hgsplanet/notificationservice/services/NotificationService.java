package com.hgsplanet.notificationservice.services;

import com.hgsplanet.notificationservice.dao.NotificationRepository;
import com.hgsplanet.notificationservice.documents.Notification;
import com.hgsplanet.notificationservice.enums.NotificationType;
import com.hgsplanet.notificationservice.web.UserRestClient;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class NotificationService {
    private final NotificationRepository notificationRepository;
    private final UserRestClient userRestClient;

    @Autowired
    public NotificationService(NotificationRepository notificationRepository, UserRestClient userRestClient) {
        this.notificationRepository = notificationRepository;
        this.userRestClient = userRestClient;
    }

    public Notification addNotification(Notification notification){
        return notificationRepository.save(notification);
    }

    public Notification findNotificationById(String id){
        return notificationRepository.findById(id).orElseThrow(()-> new RuntimeException("Notification Not Found"));
    }

    public List<Notification> findAllNotifications(){
        return notificationRepository.findAll();
    }

    public Notification updateNotification(Notification notification){
        return notificationRepository.save(notification);
    }

    public void deleteNotificationById(String id){
        notificationRepository.deleteById(id);
    }

    public Collection<Notification> getFullNotification(String username){
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJhdXRob3JpdGllcyI6W3siYXV0aG9yaXR5IjoiVVNFUiJ9XSwic3ViIjoiaXp1bmEtdGVzdDEiLCJpYXQiOjE2OTEyNDc5OTEsImV4cCI6MTY5MTg1Mjc5MX0.jZhAxZzob_6hQ6N0zdqA_4fjxMD0My0M6xAMqVoExXc";
        String authorization = "Bearer "+token;
        Collection<Notification> notifications = notificationRepository.findAllByUsername(username);
        for (Notification n : notifications){
            n.setUserFromEntity(userRestClient.findFullUserByUsernameForServices(n.getUserFrom(), authorization));
        }
        return notifications;
    }
}
