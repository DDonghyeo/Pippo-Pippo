package com.pippo.ppiyong.controller;

import com.pippo.ppiyong.auth.CustomUserDetail;
import com.pippo.ppiyong.domain.User;
import com.pippo.ppiyong.repository.NotificationRepository;
import com.pippo.ppiyong.service.NotificationService;
import com.pippo.ppiyong.type.Region;
import com.pippo.ppiyong.type.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;


    // 알림 조회
    @GetMapping("/notification/{region}")
    public ResponseEntity<?> findAllByRegion(@PathVariable Region region) {
        return ResponseEntity.ok(notificationService.findAllByRegion(region));
    }

    // 알림 지역 조회
    @GetMapping("/notification/region")
    public ResponseEntity<?> findAllByUserRegion(User user) {
        return ResponseEntity.ok(user.getRegion());
    }
}
