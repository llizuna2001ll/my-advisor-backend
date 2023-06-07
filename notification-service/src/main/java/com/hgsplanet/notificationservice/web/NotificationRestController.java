package com.hgsplanet.notificationservice.web;

import com.hgsplanet.notificationservice.documents.Notification;
import com.hgsplanet.notificationservice.services.NotificationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("api/v1/notifications")
public class NotificationRestController {

    private final NotificationService notificationService;

    public NotificationRestController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping
    ResponseEntity<List<Notification>> getAllNotifications(){
        List<Notification> notifications = notificationService.findAllNotifications();
        return new ResponseEntity<>(notifications, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    ResponseEntity<Notification> getNotificationById(@PathVariable String id){
        Notification notification = notificationService.findNotificationById(id);
        return new ResponseEntity<>(notification, HttpStatus.OK);
    }


    @PostMapping("/addNotification")
    ResponseEntity<Notification> addNotification(@RequestBody Notification notification){
        Notification newNotification = notificationService.addNotification(notification);
        return new ResponseEntity<>(newNotification, HttpStatus.CREATED);
    }

    @PutMapping("/updateNotification/{id}")
    ResponseEntity<Notification> updateNotification(@PathVariable String id, @RequestBody Notification notification){
        Notification updateNotification = notificationService.updateNotification(notification);
        return new ResponseEntity<>(updateNotification, HttpStatus.OK);
    }

    @DeleteMapping("/deleteNotification/{id}")
    ResponseEntity<Notification> deleteNotification(@PathVariable("id") String id){
        notificationService.deleteNotificationById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/getFullNotification/{username}")
    ResponseEntity<Collection<Notification>> getFullNotification(@PathVariable("username") String username){
        Collection<Notification> notifications = notificationService.getFullNotification(username);
        return new ResponseEntity<>(notifications, HttpStatus.OK);
    }
}
