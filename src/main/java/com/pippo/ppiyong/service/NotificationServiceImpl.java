package com.pippo.ppiyong.service;

import com.pippo.ppiyong.domain.post.Post;
import com.pippo.ppiyong.dto.NotificationResponseDto;
import com.pippo.ppiyong.repository.NotificationRepository;
import com.pippo.ppiyong.repository.PostRepository;
import com.pippo.ppiyong.repository.UserRepository;
import com.pippo.ppiyong.type.Region;
import com.pippo.ppiyong.type.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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
        Optional<Post> postList = notificationRepository.findAllByRegion(region);
        return postList.stream().map(NotificationResponseDto::new).collect(Collectors.toList());
    }
}
