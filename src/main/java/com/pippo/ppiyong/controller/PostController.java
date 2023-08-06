package com.pippo.ppiyong.controller;

import com.pippo.ppiyong.domain.post.Post;
import com.pippo.ppiyong.dto.PostRequestDto;
import com.pippo.ppiyong.dto.PostResponseDto;
import com.pippo.ppiyong.repository.PostRepository;
import com.pippo.ppiyong.service.PostServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class PostController {

    @Autowired
    PostServiceImpl postService;

    @Autowired
    PostRepository postRepository; // 테스트용...

    @GetMapping("/post/{postId}")
    public ResponseEntity<?> getPost(@PathVariable("postId") Long postId) {
        try {
            Optional<Post> postOptional = postService.findById(postId);
            if(postOptional.isPresent()) {
                return ResponseEntity.ok().body(new PostResponseDto(postOptional.get()));
            }
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //테스트용
    @PostMapping("/post")
    public ResponseEntity<?> createPost(@RequestBody PostRequestDto postRequestDto) {
        try {
            postRepository.save(new Post(postRequestDto));
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //테스트용
    @GetMapping("/posts")
    public ResponseEntity<?> getPosts() {
        try {
            List<Post> postList = postRepository.findAll();
            return ResponseEntity.ok().body(postList.stream().map(PostResponseDto::new).toList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
