package com.pippo.ppiyong.repository;

import com.pippo.ppiyong.domain.post.CommentHate;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentHateRepository extends JpaRepository<CommentHate, Long> {

    //List<CommentHate> findByCommentId(Long commentId);

    Optional<CommentHate> findByCommentIdAndUserId(Long commentId, Long userId);
}
