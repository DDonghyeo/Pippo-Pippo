package com.pippo.ppiyong.repository;

import com.pippo.ppiyong.domain.post.CommentHate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentHateRepository extends JpaRepository<CommentHate, Long> {

    List<CommentHate> findByCommentId(Long commentId);
}
