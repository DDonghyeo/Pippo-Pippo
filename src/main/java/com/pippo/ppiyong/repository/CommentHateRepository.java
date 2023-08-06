package com.pippo.ppiyong.repository;

import com.pippo.ppiyong.domain.post.CommentHate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentHateRepository extends JpaRepository<CommentHate, Long> {
}
