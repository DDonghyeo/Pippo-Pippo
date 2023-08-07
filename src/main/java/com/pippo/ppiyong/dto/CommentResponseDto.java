package com.pippo.ppiyong.dto;

import com.pippo.ppiyong.domain.post.Comment;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter @Setter
public class CommentResponseDto {

    private Long id;

    private String location;

    private String name;

    private String content;

    private String createdAt;

    private int like;

    private int hate;

    private boolean isLike;

    private boolean isHate;

    private String imageUrl;

    public CommentResponseDto(Comment comment) {
        this.id = comment.getId();
        this.location = null;//어..............
        String name = comment.getUser().getNickName();
        this.name = name.charAt(0) + "*".repeat(name.length() - 1);
        this.content = comment.getContent();
        this.createdAt = comment.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd a hh:mm"));
        this.like = comment.getLikers().size();
        this.hate = comment.getHaters().size();
        this.isLike = false;
        this.isHate = false;
        this.imageUrl = comment.getImageUrl();
    }

    public CommentResponseDto(Comment comment, boolean isLike, boolean isHate) {
        this.id = comment.getId();
        this.location = null;//어..............
        String name = comment.getUser().getNickName();
        this.name = name.charAt(0) + "*".repeat(name.length() - 1);
        this.content = comment.getContent();
        this.createdAt = comment.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd a hh:mm"));
        this.like = comment.getLikers().size();
        this.hate = comment.getHaters().size();
        this.isLike = isLike;
        this.isHate = isHate;
        this.imageUrl = comment.getImageUrl();
    }

    public boolean getIsLike() {
        return isLike;
    }

    public boolean getIsHate() {
        return isHate;
    }


}
