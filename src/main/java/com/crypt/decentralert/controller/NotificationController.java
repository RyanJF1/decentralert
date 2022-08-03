package com.crypt.decentralert.controller;

import com.crypt.decentralert.service.AddressService;
import com.crypt.decentralert.service.NotificationService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api")
public class NotificationController {
    private final NotificationService notificationService;
    private final AddressService addressService;

    public NotificationController(NotificationService notificationService, AddressService addressService) {
        this.notificationService = notificationService;
        this.addressService = addressService;
    }

    @Scheduled(cron = "0 0/5 * * * ?")
    @GetMapping("/notify/_AssetTransfers")
    public void notifyAssetTransfers(){
        notificationService.notifyAssetTransfers();
    }
}
