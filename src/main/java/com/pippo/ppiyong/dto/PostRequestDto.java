package com.pippo.ppiyong.dto;

import com.pippo.ppiyong.type.Region;
import com.pippo.ppiyong.type.Type;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PostRequestDto {

    private Type type;

    private String title;

    private String content;

    private Region region;
}
