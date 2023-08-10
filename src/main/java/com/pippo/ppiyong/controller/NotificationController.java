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

    // 알림 조회
    @GetMapping("/notification/{region}")
    public ResponseEntity<?> findAllByRegion(@PathVariable Region region) {
        return ResponseEntity.ok(notificationService.findAllByRegion(region));
    }

    // 알림 지역 조회
    @GetMapping("/notification/region")
    public ResponseEntity<List<NotificationResponseDto>> findByRegion() {
        String userEmail = "test@gmail.com"; // 조회 하고자 하는 이메일
        User user = userRepository.findByEmail(userEmail).orElse(null);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        List<NotificationResponseDto> responseDtoList = notificationService.findAllByRegion(user.getRegion());
        return ResponseEntity.ok().body(responseDtoList);
    }


}

// 알림 지역 설정
//    @PutMapping("/notification/region")
//    public ResponseEntity<?> updateRegion(User user) {
//        return ResponseEntity.ok();
//    }

