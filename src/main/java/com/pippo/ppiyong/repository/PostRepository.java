package com.pippo.ppiyong.repository;

import com.pippo.ppiyong.domain.post.Post;
import com.pippo.ppiyong.type.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findByRegion(Region region);

}
