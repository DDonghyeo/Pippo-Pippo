package com.pippo.ppiyong.repository;

import com.pippo.ppiyong.domain.post.CommentLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentLikeRepository extends JpaRepository<CommentLike, Long> {

    List<CommentLike> findByCommentId(Long commentId);

    Optional<CommentLike> findByCommentIdAndUserId(Long commentId, Long userId);
}
