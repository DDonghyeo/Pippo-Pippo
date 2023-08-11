package com.pippo.ppiyong.domain.post;

import com.pippo.ppiyong.domain.User;
import com.pippo.ppiyong.dto.CommentRequestDto;
import com.pippo.ppiyong.type.BaseTimeEntity;
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
public class Comment extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private String content;

    //@OneToMany(mappedBy = "comment")
    //private List<Image> imageList;

    private String imageUrl;

    private String location;

    @OneToMany(mappedBy = "comment")
    private List<CommentHate> haters;

    @OneToMany(mappedBy = "comment")
    private List<CommentLike> likers;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    public Comment(CommentRequestDto commentRequestDto, String imageUrl, User user, Post post) {
        this.content = commentRequestDto.getContent();
        this.imageUrl = imageUrl;
        this.location = commentRequestDto.getLocation();
        this.user = user;
        this.post = post;
    }
}
