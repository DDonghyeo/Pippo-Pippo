package com.pippo.ppiyong.dto;

import com.pippo.ppiyong.domain.User;
import com.pippo.ppiyong.type.Region;

public class RegionResponseDto {
    private Region region;

    public RegionResponseDto(User user) {
        this.region = user.getRegion();
    }
}
