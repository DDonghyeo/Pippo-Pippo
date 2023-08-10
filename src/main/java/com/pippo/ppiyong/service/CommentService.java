package com.pippo.ppiyong.service;

import com.pippo.ppiyong.domain.User;
import com.pippo.ppiyong.domain.post.Comment;
import com.pippo.ppiyong.domain.post.Post;
import com.pippo.ppiyong.dto.CommentRequestDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

public interface CommentService {

    void save(CommentRequestDto commentRequestDto, MultipartFile file, User user, Post post);

    Optional<Comment> findById(Long id);

    void updateLike(Comment comment, User user);

    void updateHate(Comment comment, User user);

    boolean isLike(Comment comment, User user);

    boolean isHate(Comment comment, User user);
}
