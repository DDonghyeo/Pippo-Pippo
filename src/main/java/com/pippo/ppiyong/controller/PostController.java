package com.pippo.ppiyong.controller;

import com.pippo.ppiyong.auth.CustomUserDetail;
import com.pippo.ppiyong.domain.User;
import com.pippo.ppiyong.domain.post.Comment;
import com.pippo.ppiyong.domain.post.Post;
import com.pippo.ppiyong.dto.CommentResponseDto;
import com.pippo.ppiyong.dto.PostResponseDto;
import com.pippo.ppiyong.service.CommentServiceImpl;
import com.pippo.ppiyong.service.PostServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
public class PostController {

    @Autowired
    PostServiceImpl postService;

    @Autowired
    CommentServiceImpl commentService;

    @GetMapping("/post/{postId}")
    public ResponseEntity<?> getPost(@PathVariable("postId") Long postId, @AuthenticationPrincipal CustomUserDetail customUserDetail) {
        try {
            Optional<Post> postOptional = postService.findById(postId);
            if(postOptional.isPresent()) {
                Post post = postOptional.get();
                List<Comment> commentList = post.getCommentList();

                PostResponseDto postResponseDto;
                if(customUserDetail != null) {
                    List<CommentResponseDto> commentResponseDtoList = new ArrayList<>();
                    for(Comment comment : commentList) {
                        User user = customUserDetail.getUser();
                        commentResponseDtoList.add(new CommentResponseDto(comment, commentService.isLike(comment, user), commentService.isHate(comment, user)));
                    }
                    postResponseDto = new PostResponseDto(post, commentResponseDtoList);
                } else {
                    postResponseDto = new PostResponseDto(post);
                }

                return ResponseEntity.ok().body(postResponseDto);
            }
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
