package com.pippo.ppiyong.controller;

import com.pippo.ppiyong.dto.HomeResponseDto;
import com.pippo.ppiyong.service.NewsServiceImpl;
import com.pippo.ppiyong.service.PostServiceImpl;
import com.pippo.ppiyong.type.Region;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
public class HomeController {

    @Autowired
    PostServiceImpl postService;

    @Autowired
    NewsServiceImpl newsService;

    @GetMapping("/home")
    public ResponseEntity<?> getHome(@RequestParam("region") Region region) {
        try {
            HomeResponseDto homeResponseDto = postService.findPosts(region);
            homeResponseDto.setNews(newsService.searchNews());
            return ResponseEntity.ok().body(homeResponseDto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}
