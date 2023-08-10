package com.pippo.ppiyong.dto.Notification;

import com.pippo.ppiyong.domain.post.Post;
import com.pippo.ppiyong.type.Region;
import com.pippo.ppiyong.type.Type;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.format.DateTimeFormatter;

@Getter
@Setter
public class NotificationResponseDto {
    private Long id;
    private String title;
    private String content;
    private final String time;

    public NotificationResponseDto(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.time = post.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd a hh:mm"));
    }
}
