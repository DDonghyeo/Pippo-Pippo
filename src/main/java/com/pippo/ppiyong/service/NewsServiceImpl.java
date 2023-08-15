package com.pippo.ppiyong.service;

import com.pippo.ppiyong.domain.news.News;
import com.pippo.ppiyong.domain.news.NewsData;
import com.pippo.ppiyong.domain.news.NewsJson;
import com.pippo.ppiyong.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

@Service
public class NewsServiceImpl implements NewsService {

    @Autowired
    NewsRepository newsRepository;

    @Value("${naver.api.clientId}")
    private String clientId;

    @Value("${naver.api.clientSecret}")
    private String clientSecret;

    @Override
    public void saveAll(List<News> newsList) {
        try {
            newsRepository.saveAll(newsList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteAll() {
        try {
            newsRepository.deleteAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<News> findAll() {
        try {
            return newsRepository.findAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<News> searchNews() {
        try {
            List<NewsData> newsDataList = new ArrayList<>();
            newsDataList.addAll(Objects.requireNonNull(searchAndFilter("태풍")));
            newsDataList.addAll(Objects.requireNonNull(searchAndFilter("호우")));
            newsDataList.addAll(Objects.requireNonNull(searchAndFilter("폭염")));
            newsDataList.addAll(Objects.requireNonNull(searchAndFilter("폭설")));
            newsDataList.addAll(Objects.requireNonNull(searchAndFilter("지진")));
            newsDataList.addAll(Objects.requireNonNull(searchAndFilter("해일")));
            //newsDataList.addAll(Objects.requireNonNull(searchAndFilter("테러")));
            //newsDataList.addAll(Objects.requireNonNull(searchAndFilter("전쟁")));
            newsDataList.addAll(Objects.requireNonNull(searchAndFilter("미사일")));

            if(newsDataList.isEmpty()) {
                return null;
            }

            List<News> sortedList = sortByDate(newsDataList);
            if(sortedList != null) {
                return sortedList.subList(0, 5);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private List<News> sortByDate(List<NewsData> newsDataList) {
        try {
            List<News> newsList = new ArrayList<>(newsDataList.stream().map(News::new).toList());

            newsList.sort(Comparator.comparing(News::getDate).reversed());

            return newsList;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private List<NewsData> searchAndFilter(String keyword) {
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
                List<NewsData> result = new ArrayList<>();
                for(NewsData newsData : newsJson.getItems()) {
                    if(newsData.getTitle().contains(keyword)) {
                        result.add(newsData);
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
