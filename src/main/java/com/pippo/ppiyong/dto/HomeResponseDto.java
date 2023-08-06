package com.pippo.ppiyong.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class HomeResponseDto {

    private List<HomePostResponseDto> weather;

    private List<HomePostResponseDto> earthquake;

    private List<HomePostResponseDto> civil;

    private List<HomePostResponseDto> lost;

}
