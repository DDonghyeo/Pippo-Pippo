package com.pippo.ppiyong.service;

import com.pippo.ppiyong.domain.User;
import com.pippo.ppiyong.domain.post.Comment;
import com.pippo.ppiyong.domain.post.CommentHate;
import com.pippo.ppiyong.domain.post.CommentLike;
import com.pippo.ppiyong.domain.post.Post;
import com.pippo.ppiyong.dto.CommentRequestDto;
import com.pippo.ppiyong.repository.CommentHateRepository;
import com.pippo.ppiyong.repository.CommentLikeRepository;
import com.pippo.ppiyong.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;


@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    CommentLikeRepository commentLikeRepository;

    @Autowired
    CommentHateRepository commentHateRepository;

    @Autowired
    S3UploaderService uploaderService;

    @Override
    public void save(CommentRequestDto commentRequestDto, MultipartFile file, User user, Post post) {
        try {
            String imageUrl;
            if(file.isEmpty()) {
                imageUrl = null;
            } else {
                imageUrl = uploaderService.upload(file);
            }
            commentRepository.save(new Comment(commentRequestDto, imageUrl, user, post));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Comment> findById(Long id) {
        try {
            return commentRepository.findById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public void updateLike(Comment comment, User user) {
        try {
            List<CommentLike> commentLikeList = comment.getLikers();
            for(CommentLike commentLike : commentLikeList) {
                if(commentLike.getUser().equals(user)) {
                    commentLikeRepository.delete(commentLike);
                    System.out.println(">>>like cancel: " + user.getEmail());
                    return;
                }
            }
            commentLikeRepository.save(new CommentLike(comment, user));
            System.out.println(">>> like: " + user.getEmail());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateHate(Comment comment, User user) {
        try {
            List<CommentHate> commentHateList = comment.getHaters();
            for(CommentHate commentHate : commentHateList) {
                if(commentHate.getUser().equals(user)) {
                    commentHateRepository.delete(commentHate);
                    System.out.println(">>>hate cancel: " + user.getEmail());
                    return;
                }
            }
            commentHateRepository.save(new CommentHate(comment, user));
            System.out.println(">>> hate: " + user.getEmail());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean isLike(Comment comment, User user) {
        try {
            List<CommentLike> commentLikeList = commentLikeRepository.findByCommentId(comment.getId());
            for(CommentLike commentLike : commentLikeList) {
                if(commentLike.getUser().equals(user)) {
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean isHate(Comment comment, User user) {
        try {
            List<CommentHate> commentHateList = commentHateRepository.findByCommentId(comment.getId());
            for(CommentHate commentHate : commentHateList) {
                if(commentHate.getUser().equals(user)) {
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
