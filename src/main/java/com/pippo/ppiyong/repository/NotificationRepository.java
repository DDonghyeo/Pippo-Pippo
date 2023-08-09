package com.pippo.ppiyong.repository;

import com.pippo.ppiyong.domain.post.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NotificationRepository extends JpaRepository<Post, Long> {

    Optional<Post> findById(Long id);
}
