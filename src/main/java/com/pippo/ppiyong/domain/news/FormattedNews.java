package com.pippo.ppiyong.domain.news;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Getter @Setter
public class FormattedNews {

    private String title;

    private String url;

    private LocalDateTime date;

    public FormattedNews(News news) {
        this.title = news.getTitle();
        this.url = news.getUrl();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("E, d MMM yyyy HH:mm:ss Z", Locale.ENGLISH);
        this.date = LocalDateTime.parse(news.getDate(), formatter);
    }
}
