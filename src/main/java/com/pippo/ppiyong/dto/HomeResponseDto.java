package com.pippo.ppiyong.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class HomeResponseDto {

    private List<NewsResponseDto> news;

    private List<HomePostResponseDto> weather;

    private List<HomePostResponseDto> earthquake;

    private List<HomePostResponseDto> civil;

    private List<HomePostResponseDto> lost;

    public HomeResponseDto(List<HomePostResponseDto> weather, List<HomePostResponseDto> earthquake, List<HomePostResponseDto> civil, List<HomePostResponseDto> lost) {
        this.weather = weather;
        this.earthquake = earthquake;
        this.civil = civil;
        this.lost = lost;
    }

}
