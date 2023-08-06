package com.pippo.ppiyong.repository;

import com.pippo.ppiyong.domain.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Long> {

    Optional<List<Task>> findAllByCheckList_Id(Long check_list_id);
}
