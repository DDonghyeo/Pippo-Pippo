package com.pippo.ppiyong.domain.news;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown=true)
public class NewsData {

    private String title;

    @JsonProperty("link")
    private String url;

    @JsonProperty("pubDate")
    private String date;
}
