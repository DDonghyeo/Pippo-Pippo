package com.pippo.ppiyong.service;

import com.pippo.ppiyong.dto.NewsResponseDto;

import java.util.List;

public interface NewsService {

    List<NewsResponseDto> searchNews();
}
