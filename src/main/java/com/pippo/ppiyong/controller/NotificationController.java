package com.pippo.ppiyong.controller;

import com.pippo.ppiyong.auth.CustomUserDetail;
import com.pippo.ppiyong.domain.User;
import com.pippo.ppiyong.domain.post.Post;
import com.pippo.ppiyong.dto.CategoryResponseDto;
import com.pippo.ppiyong.dto.NotificationResponseDto;
import com.pippo.ppiyong.dto.RegionRequestDto;
import com.pippo.ppiyong.dto.RegionResponseDto;
import com.pippo.ppiyong.repository.NotificationRepository;
import com.pippo.ppiyong.repository.UserRepository;
import com.pippo.ppiyong.service.NotificationService;
import com.pippo.ppiyong.type.Region;
import com.pippo.ppiyong.type.Type;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    @Operation(summary = "알림 조회", description = "Request 요청시 유저의 지역, 카테고리 정보 기반으로 post를 보내줌")
    @GetMapping("/notification")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<NotificationResponseDto>> findAllByRegionAndCategory(@AuthenticationPrincipal CustomUserDetail customUserDetail) {
        User user = customUserDetail.getUser();

        if (user == null) {
            // 유효하지 않은 사용자 처리 로직
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        List<NotificationResponseDto> responseDtoList = notificationService.findAllByRegionAndCategory(user);
        return ResponseEntity.ok(responseDtoList);
    }


    // 알림 지역 조회
    @Operation(summary = "알림 지역 조회", description = "Request 요청시 유저가 설정한 지역의 이름을 보내줌")
    @GetMapping("/notification/region")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<RegionResponseDto> getUserRegion(@AuthenticationPrincipal CustomUserDetail customUserDetail) {
        User user = customUserDetail.getUser();

        if (user == null) {
            // 유효하지 않은 사용자 처리 로직
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        RegionResponseDto userRegion = notificationService.getUserRegionByEmail(user.getEmail());

        if (userRegion != null) {
            return ResponseEntity.ok(userRegion);
        } else {
            return ResponseEntity.notFound().build(); // 또는 다른 응답코드 사용 가능
        }
    }

    // 알림 카테고리 조회
    @Operation(summary = "알림 카테고리 조회", description = "Request 요청시 유저의 카테고리 정보를 보내줌")
    @GetMapping("/notification/category")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<CategoryResponseDto> getUserCategory(@AuthenticationPrincipal CustomUserDetail customUserDetail) {
        User user = customUserDetail.getUser();

        CategoryResponseDto categoryResponseDto = notificationService.getUserCategoryByEmail(user.getEmail());

        return ResponseEntity.ok(categoryResponseDto); // null이면 기본 생성자로 생성된 값이 반환됨
    }

    // 알림 지역 설정
    @Operation(summary = "알림 지역 설정", description = "Request Body에 { 'region' : '서울특별시'}와 같은 형태로 요청")
    @PutMapping("/notification/region")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<String> updateNotificationRegion(@RequestBody RegionRequestDto regionRequestDto, @AuthenticationPrincipal CustomUserDetail customUserDetail) {
        User user = customUserDetail.getUser();

        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }

        String requestedRegionName = regionRequestDto.getRegion();

        // Convert the English region name to Region enum using fromStringInEnglish
        Region requestedRegion = Region.fromStringInEnglish(requestedRegionName);

        if (requestedRegion == null) {
            return ResponseEntity.badRequest().body("Invalid region: " + requestedRegionName);
        }

        user.setRegion(requestedRegion);
        userRepository.save(user);

        return ResponseEntity.ok("User's region updated successfully");
    }
}

