package com.pippo.ppiyong.service;

import com.pippo.ppiyong.domain.User;
import com.pippo.ppiyong.dto.NotificationRequestDto;
import com.pippo.ppiyong.dto.NotificationResponseDto;
import com.pippo.ppiyong.type.Region;
import com.pippo.ppiyong.type.Type;

import java.util.List;

public interface NotificationService {

    // 알림 조회
    List<NotificationResponseDto> findById(Long id);
}
