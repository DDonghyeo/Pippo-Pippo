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

    public void save(CommentRequestDto commentRequestDto, User user, Post post) {
        try {
            commentRepository.save(new Comment(commentRequestDto, user, post));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Optional<Comment> findById(Long id) {
        try {
            return commentRepository.findById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

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
}
