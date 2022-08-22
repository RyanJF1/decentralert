package com.crypt.decentralert.controller;

import com.crypt.decentralert.service.NotificationService;
import com.crypt.decentralert.service.UIService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api")
public class UIController {
    private final UIService uiService;

    public UIController(UIService uiService) {
        this.uiService = uiService;
    }

    @GetMapping("/ui/dashboard")
    public ResponseEntity<?> getDashboard(){

        return ResponseEntity.ok().body(uiService.getDashboard());
    }
}
