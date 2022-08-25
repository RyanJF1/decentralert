package com.crypt.decentralert.controller;

import com.crypt.decentralert.request.CreateNotificationRequest;
import com.crypt.decentralert.response.HistoryResponse;
import com.crypt.decentralert.response.NotificationResponse;
import com.crypt.decentralert.service.AddressService;
import com.crypt.decentralert.service.NotificationService;
import org.springframework.data.repository.query.Param;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/api")
public class NotificationController {
    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PostMapping(value = "/notifications", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createNotification(@RequestBody CreateNotificationRequest request,
                                                HttpServletRequest httpServletRequest){
        String guid = httpServletRequest.getHeader("x-user-token");
        notificationService.createNotification(guid, request);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/notifications")
    public ResponseEntity<?> getNotifications(HttpServletRequest httpServletRequest){
        String guid = httpServletRequest.getHeader("x-user-token");
        List<NotificationResponse> responseList = notificationService.getNotifications(guid);
        return ResponseEntity.ok().body(responseList);
    }

    @DeleteMapping("/notifications/{guid}")
    public ResponseEntity<?> deleteNotification(@PathVariable("guid") String guid){
        notificationService.deleteNotification(guid);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/notifications/{guid}/_disable")
    public ResponseEntity<?> disableNotification(@PathVariable("guid") String guid){
        List<NotificationResponse> response = notificationService.disableNotification(guid);
        return ResponseEntity.ok().body(response);
    }

    @Scheduled(cron = "0 0/5 * * * ?")
    @GetMapping("/notifications/notify/_AssetTransfers")
    public void notifyAssetTransfers(){
        notificationService.notifyMultiAssetTransfers();
    }

    @GetMapping("/notifications/history")
    public ResponseEntity<?> getAllHistory(HttpServletRequest httpServletRequest){
        String guid = httpServletRequest.getHeader("x-user-token");
        List<HistoryResponse> responseList = notificationService.getAllHistory(guid);
        return ResponseEntity.ok().body(responseList);
    }
}
