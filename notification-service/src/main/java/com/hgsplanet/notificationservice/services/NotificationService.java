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

    public Collection<Notification> findAlertNotificationByUsername(String username){
        return notificationRepository.findAllByUserToAndNotificationType(username, NotificationType.ALERT);
    }

    public Collection<Notification> findMessageNotificationByUsername(String username){
        return notificationRepository.findAllByUserToAndNotificationType(username, NotificationType.MESSAGE);
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

    public Notification getFullNotification(String notificationId){
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJhdXRob3JpdGllcyI6W3siYXV0aG9yaXR5IjoiVVNFUiJ9XSwic3ViIjoiaXp1bmEtdGVzdDEiLCJpYXQiOjE2ODQ2OTA4MjAsImV4cCI6MTY4NTI5NTYyMH0.aeReM465Ja26TKzv4ctys0BR4YPynBqd7yrji9G-mCY";
        String authorization = "Bearer "+token;
        Notification notification = notificationRepository.findById(notificationId).get();
        notification.setUserFromEntity(userRestClient.findFullUserByUsernameForServices(notification.getUserFrom(), authorization));
        return notification;
    }
}
