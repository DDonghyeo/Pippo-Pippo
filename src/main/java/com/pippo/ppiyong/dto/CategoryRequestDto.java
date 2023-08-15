package com.pippo.ppiyong.dto;

import com.pippo.ppiyong.type.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class CategoryRequestDto {
    private int weather;
    private int earthquake;
    private int civil;
    private int lost;
}


