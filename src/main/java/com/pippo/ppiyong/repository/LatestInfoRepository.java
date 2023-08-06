package com.pippo.ppiyong.repository;

import com.pippo.ppiyong.domain.LatestInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LatestInfoRepository extends JpaRepository<LatestInfo, Long> {
}
