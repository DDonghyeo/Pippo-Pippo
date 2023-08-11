package com.pippo.ppiyong.dto;

import com.pippo.ppiyong.domain.news.News;
import lombok.Getter;

@Getter
public class NewsResponseDto {

    private String title;

    private String url;

    public NewsResponseDto(News news) {
        this.title = news.getTitle().replaceAll("<b>", "")
                .replaceAll("</b>", "");
        this.url = news.getUrl();
    }
}
