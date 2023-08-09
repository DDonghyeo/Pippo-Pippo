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
public class NotificationRequestDto {
    private Region region;
    private Type type;

    public Post toEntity() {
        return Post.builder()
                .region(region)
                .type(type)
                .build();
    }
}
