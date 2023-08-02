package com.pippo.ppiyong.domain;

import com.pippo.ppiyong.domain.Task;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table(name = "CheckList")
public class CheckList {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;

    @OneToMany(mappedBy = "task_id")
    private List<Task> tasks;
}
