package com.pippo.ppiyong.dto;

import com.pippo.ppiyong.domain.post.Post;
import com.pippo.ppiyong.type.Type;
import lombok.Getter;
import lombok.Setter;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Getter @Setter
public class PostResponseDto {

    private String from;

    private Type category;

    private String content;

    private String time;

    private List<CommentResponseDto> comment;

    public PostResponseDto(Post post) {
        this.from = post.getTitle();
        this.category = post.getType();
        this.content = post.getContent();
        this.time = post.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd a hh:mm"));
        this.comment = post.getCommentList().stream().map(CommentResponseDto::new).collect(Collectors.toList());
    }

    public PostResponseDto(Post post, List<CommentResponseDto> commentList) {
        this.from = post.getTitle();
        this.category = post.getType();
        this.content = post.getContent();
        this.time = post.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd a hh:mm"));
        this.comment = commentList;
    }
}
