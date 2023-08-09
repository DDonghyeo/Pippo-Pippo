package com.pippo.ppiyong.dto;

import com.pippo.ppiyong.domain.post.Post;
import com.pippo.ppiyong.type.Region;
import com.pippo.ppiyong.type.Type;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NotificationResponseDto {
    private Long id;
    private Type type;
    private String title;
    private String content;
    private Integer views;
    private Region region;

    public NotificationResponseDto(Post post) {
        this.id = post.getId();
        this.type = post.getType();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.views = post.getViews();
        this.region = post.getRegion();
    }
}
