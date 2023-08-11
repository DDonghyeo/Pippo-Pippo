package com.pippo.ppiyong.dto;

import com.pippo.ppiyong.domain.User;
import com.pippo.ppiyong.type.Region;
import lombok.Getter;

@Getter
public class RegionResponseDto {
    private String region;

    public RegionResponseDto(Region region) {
        if (region != null) {
            this.region = region.name();
        }
    }

    // Getter and Setter for regionName
}




