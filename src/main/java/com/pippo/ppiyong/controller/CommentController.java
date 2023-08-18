package com.pippo.ppiyong.controller;

import com.pippo.ppiyong.auth.CustomUserDetail;
import com.pippo.ppiyong.domain.User;
import com.pippo.ppiyong.domain.post.Comment;
import com.pippo.ppiyong.domain.post.Post;
import com.pippo.ppiyong.dto.CommentRequestDto;
import com.pippo.ppiyong.service.CommentServiceImpl;
import com.pippo.ppiyong.service.PostServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Transactional
@RestController
@RequestMapping("/api")
public class CommentController {

    @Autowired
    PostServiceImpl postService;

    @Autowired
    CommentServiceImpl commentService;

    @Operation(summary = "댓글 작성", description = "form-data로 요청.\ndata: location, content (application/json)\nfile: 이미지파일")
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

    @Operation(summary = "댓글 좋아요")
    @PreAuthorize("hasRole('USER')")
    @PostMapping("/comment/{commentId}/like")
    public ResponseEntity<?> likeComment(@PathVariable("commentId") Long commentId, @AuthenticationPrincipal CustomUserDetail customUserDetail) {
        try {
            User user = customUserDetail.getUser();
            Optional<Comment> commentOptional = commentService.findById(commentId);
            if(commentOptional.isPresent()) {
                Comment comment = commentOptional.get();
                System.out.println("commentId: " + comment.getId() + " userEmail: " + user.getEmail() + " ishate: " + commentService.isHate(comment, user));
                if (commentService.isHate(comment, user)) {
                    commentService.updateHate(comment, user);
                    System.out.println("hate cancel");
                }

                commentService.updateLike(comment, user);
                return ResponseEntity.ok().body("{\"message\": \"요청에 성공했습니다.\"}");
            }
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Operation(summary = "댓글 싫어요")
    @PreAuthorize("hasRole('USER')")
    @PostMapping("/comment/{commentId}/hate")
    public ResponseEntity<?> hateComment(@PathVariable("commentId") Long commentId, @AuthenticationPrincipal CustomUserDetail customUserDetail) {
        try {
            User user = customUserDetail.getUser();
            Optional<Comment> commentOptional = commentService.findById(commentId);
            if(commentOptional.isPresent()) {
                Comment comment = commentOptional.get();
                System.out.println("commentId: " + comment.getId() + " userEmail: " + user.getEmail() + " islike: " + commentService.isLike(comment, user));
                if (commentService.isLike(comment, user)) {
                    commentService.updateLike(comment, user);
                    System.out.println("like cancel");
                }

                commentService.updateHate(comment, user);
                return ResponseEntity.ok().body("{\"message\": \"요청에 성공했습니다.\"}");
            }
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
