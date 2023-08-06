package com.pippo.ppiyong.controller;

import com.pippo.ppiyong.repository.NotificationRepository;
import com.pippo.ppiyong.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/notification")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;


    // 알림 조회
    @GetMapping("")
    public ResponseEntity<?> findAllByPostId(Long postId) {
        return ResponseEntity.ok(NotificationService.findAllByPostId());
    }
}
