package com.pippo.ppiyong.repository;

import com.pippo.ppiyong.domain.SubCategory;
import com.pippo.ppiyong.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubCategoryRepository extends JpaRepository<SubCategory, Long> {

    void deleteAllByUser(User user);
}
