package com.pippo.ppiyong.controller;

import com.pippo.ppiyong.repository.NotificationRepository;
import com.pippo.ppiyong.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NotificationController {

    @Autowired
    private NotificationService notificationService;


    // 알림 조회
    @GetMapping("api/notification")
    public ResponseEntity<?> findAllByPostId(Long id) {
        return ResponseEntity.ok(notificationService.findById(id));
    }
}
