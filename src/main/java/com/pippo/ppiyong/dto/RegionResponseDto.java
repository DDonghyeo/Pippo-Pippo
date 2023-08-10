package com.pippo.ppiyong.dto;

import com.pippo.ppiyong.domain.User;
import com.pippo.ppiyong.type.Region;
import lombok.Getter;

@Getter
public class RegionResponseDto {
    private Region region;

    public RegionResponseDto(User user) {
        this.region = user.getRegion();
    }
}
