package com.pippo.ppiyong.domain.post;

import com.pippo.ppiyong.domain.User;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "CommentLike")
public class CommentLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_like_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_id", nullable = false)
    private Comment comment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
