package com.pippo.ppiyong.domain.post;

import com.pippo.ppiyong.domain.User;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "CommentLike")
public class CommentLike {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(mappedBy = "comment_id")
    private Comment comment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
