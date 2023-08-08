package com.pippo.ppiyong.controller;

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

    @GetMapping("/home")
    public ResponseEntity<?> getHome(@RequestParam("region") Region region) {
        try {
            //Region region = Region.valueOf(location);
            return ResponseEntity.ok().body(postService.findPosts(region));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}
