package com.pippo.ppiyong.service;

import com.pippo.ppiyong.domain.news.News;

import java.util.List;

public interface NewsService {

    void saveAll(List<News> newsList);

    void deleteAll();

    List<News> findAll();

    List<News> searchNews();
}
