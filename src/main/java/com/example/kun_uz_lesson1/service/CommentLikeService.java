package com.example.kun_uz_lesson1.service;

import com.example.kun_uz_lesson1.config.CustomUserDetails;
import com.example.kun_uz_lesson1.dto.extra.CommentLikeDislikeCountDTO;
import com.example.kun_uz_lesson1.entity.CommentLikeEntity;
import com.example.kun_uz_lesson1.enums.LikeStatus;
import com.example.kun_uz_lesson1.repository.CommentLikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CommentLikeService {
    @Autowired
    private CommentLikeRepository commentLikeRepository;
    public CommentLikeDislikeCountDTO addStatus(String commentId, LikeStatus status) {
        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Integer profileId = userDetails.getId();

        Optional<CommentLikeEntity> optional = commentLikeRepository.findByCommentIdAndProfileId(commentId, profileId);
        if (optional.isEmpty()) {
            commentLikeRepository.save(new CommentLikeEntity(profileId, commentId, status));
        } else {
            commentLikeRepository.updateLikeType(status,commentId,profileId);
        }
        return new CommentLikeDislikeCountDTO(commentLikeRepository.countByStatusLike(LikeStatus.LIKE), commentLikeRepository.countByStatusLike(LikeStatus.DISLIKE));
    }

    public CommentLikeDislikeCountDTO delete(String commentId) {
        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Integer profileId = userDetails.getId();
        commentLikeRepository.deleteByCommentIdAndProfileId(commentId,profileId);
        return new CommentLikeDislikeCountDTO(commentLikeRepository.countByStatusLike(LikeStatus.LIKE), commentLikeRepository.countByStatusLike(LikeStatus.DISLIKE));
    }
}
