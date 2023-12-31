package com.pippo.ppiyong.controller;

import com.pippo.ppiyong.dto.HomeResponseDto;
import com.pippo.ppiyong.dto.NewsResponseDto;
import com.pippo.ppiyong.service.NewsServiceImpl;
import com.pippo.ppiyong.service.PostServiceImpl;
import com.pippo.ppiyong.type.Region;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;


@RestController
@RequestMapping("/api")
public class HomeController {

    @Autowired
    PostServiceImpl postService;

    @Autowired
    NewsServiceImpl newsService;

    @Operation(summary = "메인 페이지(뉴스 및 재난문자 조회)", description = "query string: region(api 명세서에 나와있는대로 SEOUL 이런식으로 적어주셔야해용)")
    @GetMapping("/home")
    public ResponseEntity<?> getHome(@RequestParam("region") Region region) {
        try {
            HomeResponseDto homeResponseDto = postService.findPosts(region);
            homeResponseDto.setNews(newsService.findAll().stream().map(NewsResponseDto::new).collect(Collectors.toList()));
            return ResponseEntity.ok().body(homeResponseDto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}
