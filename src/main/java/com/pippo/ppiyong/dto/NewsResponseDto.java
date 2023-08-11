package com.pippo.ppiyong.dto;

import com.pippo.ppiyong.domain.news.FormattedNews;
import lombok.Getter;

@Getter
public class NewsResponseDto {

    private String title;

    private String url;

    public NewsResponseDto(FormattedNews news) {
        this.title = news.getTitle().replaceAll("<b>", "")
                .replaceAll("</b>", "")
                .replaceAll("&apos;", "'");
        this.url = news.getUrl();
    }
}
