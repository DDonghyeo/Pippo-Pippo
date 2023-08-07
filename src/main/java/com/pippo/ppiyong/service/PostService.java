package com.pippo.ppiyong.service;

import com.pippo.ppiyong.domain.DisasterMessage;
import com.pippo.ppiyong.domain.post.Post;
import com.pippo.ppiyong.dto.HomeResponseDto;
import com.pippo.ppiyong.type.Region;

import java.util.Optional;

public interface PostService {

    void save(DisasterMessage message);

    Optional<Post> findById(Long id);

    boolean isUpdated(String id);

    HomeResponseDto findPosts(Region region);
}
