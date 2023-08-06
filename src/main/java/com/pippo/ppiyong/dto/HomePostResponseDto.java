package com.pippo.ppiyong.dto;

import com.pippo.ppiyong.domain.post.Post;
import com.pippo.ppiyong.type.Type;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class HomePostResponseDto {

    private Long id;

    private String from;

    private Type category;

    private String content;

    private LocalDateTime time;

    private int comment;

    public HomePostResponseDto(Post post) {
        this.id = post.getId();
        this.from = post.getTitle();
        this.category = post.getType();
        this.content = post.getContent();
        this.time = post.getCreatedAt();
        this.comment = post.getCommentList().size();
    }
}
