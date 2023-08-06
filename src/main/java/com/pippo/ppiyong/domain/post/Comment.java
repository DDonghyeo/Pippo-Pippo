package com.pippo.ppiyong.domain.post;

import com.pippo.ppiyong.domain.User;
import com.pippo.ppiyong.dto.CommentRequestDto;
import com.pippo.ppiyong.type.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table(name = "Comment")
public class Comment extends BaseTimeEntity {

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    //이미지 추가 필요
    public Comment(CommentRequestDto commentRequestDto, User user, Post post) {
        this.content = commentRequestDto.getContent();
        this.user = user;
        this.post = post;
        LocalDateTime now = LocalDateTime.now();
        setCreatedAt(now);
        setModifiedAt(now);
    }
}
