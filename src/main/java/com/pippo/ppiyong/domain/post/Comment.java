package com.pippo.ppiyong.domain.post;

import com.pippo.ppiyong.domain.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table(name = "Comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private String content;

    @OneToMany(mappedBy = "comment")
    private List<Image> imageList;

    @OneToMany(mappedBy = "comment")
    private List<CommentHate> haters;

    @OneToMany(mappedBy = "comment")
    private List<CommentLike> likers;
}
