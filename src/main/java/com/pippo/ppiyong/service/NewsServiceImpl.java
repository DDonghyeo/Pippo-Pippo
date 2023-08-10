package com.pippo.ppiyong.service;

import com.pippo.ppiyong.domain.news.FormattedNews;
import com.pippo.ppiyong.domain.news.News;
import com.pippo.ppiyong.domain.news.NewsJson;
import com.pippo.ppiyong.dto.NewsResponseDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

@Service
public class NewsServiceImpl implements NewsService {

    @Value("${naver.api.clientId}")
    private String clientId;

    @Value("${naver.api.clientSecret}")
    private String clientSecret;

    @Override
    public List<NewsResponseDto> searchNews() {
        try {
            List<News> newsList = new ArrayList<>();
            newsList.addAll(Objects.requireNonNull(searchAndFilter("태풍")));
            newsList.addAll(Objects.requireNonNull(searchAndFilter("호우")));
            newsList.addAll(Objects.requireNonNull(searchAndFilter("폭염")));
            newsList.addAll(Objects.requireNonNull(searchAndFilter("폭설")));
            newsList.addAll(Objects.requireNonNull(searchAndFilter("지진")));
            newsList.addAll(Objects.requireNonNull(searchAndFilter("해일")));
            newsList.addAll(Objects.requireNonNull(searchAndFilter("테러")));
            newsList.addAll(Objects.requireNonNull(searchAndFilter("전쟁")));
            newsList.addAll(Objects.requireNonNull(searchAndFilter("미사일")));

            List<FormattedNews> sortedList = sortByDate(newsList);
            if(sortedList != null) {
                return sortedList.subList(0, 5).stream().map(NewsResponseDto::new).toList();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private List<FormattedNews> sortByDate(List<News> newsList) {
        try {
            List<FormattedNews> formattedNewsList = new ArrayList<>(newsList.stream().map(FormattedNews::new).toList());

            formattedNewsList.sort(Comparator.comparing(FormattedNews::getDate).reversed());

            return formattedNewsList;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private List<News> searchAndFilter(String keyword) {
        try {
            WebClient webClient = WebClient.create();
            String url = "https://openapi.naver.com/v1/search/news.json?query=" + keyword + "&display=20&start=1&sort=sim";

            NewsJson newsJson = webClient.get()
                    .uri(url)
                    .header("X-Naver-Client-Id", clientId)
                    .header("X-Naver-Client-Secret", clientSecret)
                    .retrieve()
                    .bodyToMono(NewsJson.class)
                    .block();

            if(newsJson != null) {
                List<News> result = new ArrayList<>();
                for(News news : newsJson.getItems()) {
                    if(news.getTitle().contains(keyword)) {
                        result.add(news);
                    }
                }
                return result;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
