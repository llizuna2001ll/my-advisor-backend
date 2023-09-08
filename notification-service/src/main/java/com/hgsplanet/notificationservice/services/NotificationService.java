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
        return notificationRepository.findAllByUsername(username);
    }

    public Collection<Notification> setNotificationSeen(String username){
        Collection<Notification> notifications = getFullNotification(username);
        for (Notification notification: notifications) {
            notification.setSeen(true);
            notificationRepository.save(notification);
        }
        return notifications;
    }

}
