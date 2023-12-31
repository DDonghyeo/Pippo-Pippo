package com.pippo.ppiyong.repository;

import com.pippo.ppiyong.domain.User;
import com.pippo.ppiyong.domain.post.Post;
import com.pippo.ppiyong.type.Category;
import com.pippo.ppiyong.type.Region;
import com.pippo.ppiyong.type.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NotificationRepository extends JpaRepository<Post, Long> {

    List<Post> findAllByRegionAndCategory(Region region, Category category);

    List<Post> findAllByRegion(Region region);

    @Query("SELECT DISTINCT p.region FROM Post p")
    List<Region> findAllDistinctRegions();
}
