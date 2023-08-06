package com.pippo.ppiyong.service;

import com.pippo.ppiyong.domain.post.Post;

import java.util.Optional;

public interface PostService {

    public Optional<Post> findById(Long id);
}
