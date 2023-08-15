package com.pippo.ppiyong.dto;

import com.pippo.ppiyong.type.Category;
import com.pippo.ppiyong.type.Region;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CategoryResponseDto {
    private int weather;
    private int earthquake;
    private int civil;
    private int lost;
}
