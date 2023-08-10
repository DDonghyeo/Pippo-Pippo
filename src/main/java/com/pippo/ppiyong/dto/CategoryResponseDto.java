package com.pippo.ppiyong.dto;

import com.pippo.ppiyong.type.Category;
import com.pippo.ppiyong.type.Region;
import lombok.Getter;

@Getter
public class CategoryResponseDto {
    private int weather;
    private int earthquake;
    private int civil;
    private int lost;

    public CategoryResponseDto(Category category) {
        if (category != null) {
            this.weather = (category == Category.WEATHER) ? 1 : 0;
            this.earthquake = (category == Category.EARTHQUAKE) ? 1 : 0;
            this.civil = (category == Category.CIVIL) ? 1 : 0;
            this.lost = (category == Category.LOST) ? 1 : 0;
        }
    }
}

