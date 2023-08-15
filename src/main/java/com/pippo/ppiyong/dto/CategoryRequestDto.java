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
    private boolean weather;
    private boolean earthquake;
    private boolean civil;
    private boolean lost;
}


