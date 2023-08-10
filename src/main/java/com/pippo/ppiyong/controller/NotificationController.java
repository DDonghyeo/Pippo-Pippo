package com.pippo.ppiyong.controller;

import com.pippo.ppiyong.auth.CustomUserDetail;
import com.pippo.ppiyong.domain.User;
import com.pippo.ppiyong.domain.post.Post;
import com.pippo.ppiyong.dto.NotificationResponseDto;
import com.pippo.ppiyong.dto.RegionResponseDto;
import com.pippo.ppiyong.repository.NotificationRepository;
import com.pippo.ppiyong.repository.UserRepository;
import com.pippo.ppiyong.service.NotificationService;
import com.pippo.ppiyong.type.Region;
import com.pippo.ppiyong.type.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    // 알림 조회
    @GetMapping("/notification")
    public ResponseEntity<List<NotificationResponseDto>> findAllByRegion() {
        String userEmail = "test@gmail.com";
        User user = userRepository.findByEmail(userEmail).orElse(null);

        if (user == null) {
            // 유효하지 않은 사용자 처리 로직
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        List<NotificationResponseDto> responseDtoList = notificationService.findAllByRegion(user.getRegion());
        return ResponseEntity.ok(responseDtoList);
    }

    // 알림 지역 조회
    @GetMapping("/notification/region")
    public ResponseEntity<RegionResponseDto> getUserRegion() {
        String userEmail = "test@gmail.com"; // 원하는 사용자의 이메일
        RegionResponseDto userRegion = notificationService.getUserRegionByEmail(userEmail);

        if (userRegion != null) {
            return ResponseEntity.ok(userRegion);
        } else {
            return ResponseEntity.notFound().build(); // 또는 다른 응답코드 사용 가능
        }
    }

}

