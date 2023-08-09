package com.pippo.ppiyong.dto;

import com.pippo.ppiyong.domain.post.Post;
import com.pippo.ppiyong.type.Type;
import lombok.Getter;

import java.time.format.DateTimeFormatter;

@Getter
public class HomePostResponseDto {

    private Long id;

    private String from;

    private Type category;

    private String content;

    private String time;

    private int comment;

    public HomePostResponseDto(Post post) {
        this.id = post.getId();
        this.from = post.getTitle();
        this.category = post.getType();
        this.content = post.getContent();
        this.time = post.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd a hh:mm"));
        this.comment = post.getCommentList().size();
    }
}
