package com.pippo.ppiyong.service;

import com.pippo.ppiyong.domain.User;
import com.pippo.ppiyong.domain.post.Post;
import com.pippo.ppiyong.dto.CategoryResponseDto;
import com.pippo.ppiyong.dto.NotificationResponseDto;
import com.pippo.ppiyong.dto.RegionResponseDto;
import com.pippo.ppiyong.repository.NotificationRepository;
import com.pippo.ppiyong.repository.PostRepository;
import com.pippo.ppiyong.repository.UserRepository;
import com.pippo.ppiyong.type.Region;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    NotificationRepository notificationRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PostRepository postRepository;

    @Override
    public List<NotificationResponseDto> findAllByRegion(Region region) {
        // 게시물 가져오기
        List<Post> postList = notificationRepository.findAllByRegion(region);
        return postList.stream().map(NotificationResponseDto::new).collect(Collectors.toList());
    }

    // 알림 지역 조회
    @Override
    public List<RegionResponseDto> findAllRegions(String userEmail) {
        User user = userRepository.findByEmail(userEmail).orElse(null);
        if (user == null) {
            // 유효하지 않은 사용자 처리 로직
            return Collections.emptyList();
        }

        Region userRegion = user.getRegion();
        if (userRegion != null) {
            List<RegionResponseDto> regionResponseList = new ArrayList<>();
            regionResponseList.add(new RegionResponseDto(userRegion)); // 이 부분 수정
            return regionResponseList;
        }

        return Collections.emptyList();
    }

    @Override
    public RegionResponseDto getUserRegionByEmail(String email) {
        User user = userRepository.findByEmail(email).orElse(null);
        if (user != null && user.getRegion() != null) {
            return new RegionResponseDto(user.getRegion());
        }
        return null; // 또는 예외 처리 등을 수행할 수 있습니다.
    }

    @Override
    public CategoryResponseDto getUserCategoryByEmail(String email) {
        User user = userRepository.findByEmail(email).orElse(null);
        if (user != null && user.getCategory() != null) {
            return new CategoryResponseDto(user.getCategory());
        }

        return null;
    }

}
