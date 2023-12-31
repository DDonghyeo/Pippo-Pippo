package com.pippo.ppiyong.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table(name = "Task")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_id")
    private Long id;

    private String content;

    private boolean isComplete;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "checkList_id", nullable = false)
    private CheckList checkList;

    public void updateContent(String content) {
        this.content = content;
    }

    public void updateComplete(boolean isComplete) {
        this.isComplete = isComplete;
    }

}
