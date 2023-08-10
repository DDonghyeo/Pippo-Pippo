package com.pippo.ppiyong.service;

import com.pippo.ppiyong.dto.Notification.NotificationResponseDto;
import com.pippo.ppiyong.dto.Notification.RegionResponseDto;
import com.pippo.ppiyong.type.Region;

import java.util.List;

public interface NotificationService {

    // 알림 조회
    List<NotificationResponseDto> findAllByRegion(Region region);

    List<RegionResponseDto> findAllByUserRegion(Region region);
}
