package com.pippo.ppiyong.dto.Notification;

import com.pippo.ppiyong.domain.User;
import com.pippo.ppiyong.domain.post.Post;
import com.pippo.ppiyong.type.Region;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RegionResponseDto {

    private Region region;

    public  RegionResponseDto(Post post) {
        this.region = post.getRegion();
    }
}
