package com.pippo.ppiyong.repository;

import com.pippo.ppiyong.domain.CheckList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CheckListRepository extends JpaRepository<CheckList, Long> {

    List<CheckList> findAllByUser_Email(String user_id);
}
