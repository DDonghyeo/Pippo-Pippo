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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "check_list_id")
    private Long id;

    private String title;

    @OneToMany(mappedBy = "checkList")
    private List<Task> task;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    public void updateTitle(String title) {
        this.title = title;
    }
}
