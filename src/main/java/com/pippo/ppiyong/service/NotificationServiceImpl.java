package com.pippo.ppiyong.service;

import com.pippo.ppiyong.domain.post.Post;
import com.pippo.ppiyong.dto.NotificationRequestDto;
import com.pippo.ppiyong.dto.NotificationResponseDto;
import com.pippo.ppiyong.repository.NotificationRepository;
import com.pippo.ppiyong.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    NotificationRepository notificationRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public List<NotificationResponseDto> findAllByPostId(Long postId) {
        // 게시물 가져오기
        List<Post> postList = postRepository.findAllByPostId(postId);
        return postList.stream().map(NotificationResponseDto::new).collect(Collectors.toList());
    }

}
