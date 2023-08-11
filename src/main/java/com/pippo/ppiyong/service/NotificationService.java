package com.pippo.ppiyong.service;

import com.pippo.ppiyong.domain.User;
import com.pippo.ppiyong.dto.CategoryResponseDto;
import com.pippo.ppiyong.dto.NotificationResponseDto;
import com.pippo.ppiyong.dto.RegionResponseDto;
import com.pippo.ppiyong.type.Region;

import java.util.List;

public interface NotificationService {
    // 알림 조회
    List<NotificationResponseDto> findAllByRegion(Region region);

    List<NotificationResponseDto> findAllByRegionAndCategory(User user);

    // 알림 지역 조회
    List<RegionResponseDto> findAllRegions(String userEmail);

    // 사용자의 지역 정보 조회
    RegionResponseDto getUserRegionByEmail(String email);

    CategoryResponseDto getUserCategoryByEmail(String email);
}

