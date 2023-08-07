package com.pippo.ppiyong.dto;

import com.pippo.ppiyong.domain.post.Post;
import com.pippo.ppiyong.type.Type;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter @Setter
public class PostResponseDto {

    private String from;

    private Type category;

    private String content;

    private LocalDateTime time;

    private List<CommentResponseDto> comment;

    public PostResponseDto(Post post) {
        this.from = post.getTitle();
        this.category = post.getType();
        this.content = post.getContent();
        this.time = post.getCreatedAt();
        this.comment = post.getCommentList().stream().map(CommentResponseDto::new).collect(Collectors.toList());
    }

    public PostResponseDto(Post post, List<CommentResponseDto> commentList) {
        this.from = post.getTitle();
        this.category = post.getType();
        this.content = post.getContent();
        this.time = post.getCreatedAt();
        this.comment = commentList;
    }
}
