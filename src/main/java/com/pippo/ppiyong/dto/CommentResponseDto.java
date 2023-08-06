package com.pippo.ppiyong.dto;

import com.pippo.ppiyong.domain.post.Comment;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class CommentResponseDto {

    private String location;

    private String name;

    private String content;

    private LocalDateTime createdAt;

    private int like;

    private int hate;

    private boolean isLike;

    private boolean isHate;

    private List<String> imageList; // 이미지 여러개?

    public CommentResponseDto(Comment comment) {
        this.location = null;//어..............
        this.name = comment.getUser().getNickName();
        this.content = comment.getContent();
        this.createdAt = comment.getCreatedAt();
        this.like = comment.getLikers().size();
        this.hate = comment.getHaters().size();
        this.isLike = false; // 나중에 수정하기
        this.isHate = false; // 나중에 수정하기
        this.imageList = null; // 나중에 수정하기
    }
}
