package com.crypt.decentralert.controller;

import com.crypt.decentralert.request.CreateNotificationRequest;
import com.crypt.decentralert.response.NotificationResponse;
import com.crypt.decentralert.service.AddressService;
import com.crypt.decentralert.service.NotificationService;
import org.springframework.data.repository.query.Param;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api")
public class NotificationController {
    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PostMapping(value = "/notifications", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createNotification(@RequestBody CreateNotificationRequest request){
        notificationService.createNotification(request);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/notifications")
    public ResponseEntity<?> getNotifications(@Param("email") String email){
        List<NotificationResponse> responseList = notificationService.getNotifications(email);
        return ResponseEntity.ok().body(responseList);
    }

    @DeleteMapping("/notifications")
    public ResponseEntity<?> deleteNotification(@Param("notificationId") long notificationId){
        notificationService.deleteNotification(notificationId);
        return ResponseEntity.ok().build();
    }

    @Scheduled(cron = "*/60 * * * * *")
    @GetMapping("/notifications/notify/_AssetTransfers")
    public void notifyAssetTransfers(){
        notificationService.notifyAssetTransfers();
    }
}