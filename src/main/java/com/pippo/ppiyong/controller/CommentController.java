package com.pippo.ppiyong.controller;

import com.pippo.ppiyong.auth.CustomUserDetail;
import com.pippo.ppiyong.domain.User;
import com.pippo.ppiyong.domain.post.Comment;
import com.pippo.ppiyong.domain.post.Post;
import com.pippo.ppiyong.dto.CommentRequestDto;
import com.pippo.ppiyong.service.CommentServiceImpl;
import com.pippo.ppiyong.service.PostServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
public class CommentController {

    @Autowired
    PostServiceImpl postService;

    @Autowired
    CommentServiceImpl commentService;

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/post/{postId}")
    public ResponseEntity<?> createComment(@PathVariable("postId") Long postId,
                                           @RequestPart(value = "data") CommentRequestDto commentRequestDto,
                                           @RequestPart(value = "file", required = false)MultipartFile file,
                                           @AuthenticationPrincipal CustomUserDetail customUserDetail) {
        try {
            User user = customUserDetail.getUser();
            Optional<Post> postOptional = postService.findById(postId);
            if(postOptional.isPresent()) {
                commentService.save(commentRequestDto, file, user, postOptional.get());
                return new ResponseEntity<>(HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/comment/{commentId}/like")
    public ResponseEntity<?> likeComment(@PathVariable("commentId") Long commentId, @AuthenticationPrincipal CustomUserDetail customUserDetail) {
        try {
            User user = customUserDetail.getUser();
            Optional<Comment> commentOptional = commentService.findById(commentId);
            if(commentOptional.isPresent()) {
                commentService.updateLike(commentOptional.get(), user);
                return ResponseEntity.ok().body("{\"message\": \"요청에 성공했습니다.\"}");
            }
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/comment/{commentId}/hate")
    public ResponseEntity<?> hateComment(@PathVariable("commentId") Long commentId, @AuthenticationPrincipal CustomUserDetail customUserDetail) {
        try {
            User user = customUserDetail.getUser();
            Optional<Comment> commentOptional = commentService.findById(commentId);
            if(commentOptional.isPresent()) {
                commentService.updateHate(commentOptional.get(), user);
                return ResponseEntity.ok().body("{\"message\": \"요청에 성공했습니다.\"}");
            }
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
