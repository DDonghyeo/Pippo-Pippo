package com.pippo.ppiyong.service;

import com.pippo.ppiyong.domain.User;
import com.pippo.ppiyong.dto.NotificationResponseDto;
import com.pippo.ppiyong.dto.RegionResponseDto;
import com.pippo.ppiyong.type.Region;

import java.util.List;

public interface NotificationService {

    // 알림 조회
    List<NotificationResponseDto> findAllByRegion(Region region);

    // 알림 지역 조회
    List<RegionResponseDto> findByRegion(User user);
}
