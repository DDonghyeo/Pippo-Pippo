package com.pippo.ppiyong.service;

import com.pippo.ppiyong.domain.DisasterMessage;
import com.pippo.ppiyong.domain.post.Post;

import java.util.Optional;

public interface PostService {

    public void save(DisasterMessage message);

    public Optional<Post> findById(Long id);

    public boolean isUpdated(String id);
}
