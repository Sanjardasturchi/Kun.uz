package com.example.kun_uz_lesson1.service;

import com.example.kun_uz_lesson1.config.CustomUserDetails;
import com.example.kun_uz_lesson1.dto.extra.ArticleLikeDislikeCountDTO;
import com.example.kun_uz_lesson1.entity.ArticleLikeEntity;
import com.example.kun_uz_lesson1.enums.LikeStatus;
import com.example.kun_uz_lesson1.repository.ArticleLikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ArticleLikeService {
    @Autowired
    private ArticleLikeRepository articleLikeRepository;

    public ArticleLikeDislikeCountDTO addStatus(String articleId, LikeStatus status) {
        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Integer profileId = userDetails.getId();

        Optional<ArticleLikeEntity> optional = articleLikeRepository.findByArticleIdAndProfileId(articleId, profileId);
        if (optional.isEmpty()) {
            articleLikeRepository.save(new ArticleLikeEntity(profileId, articleId, status));
        } else {
            articleLikeRepository.updateLikeType(status,articleId,profileId);
        }
        return new ArticleLikeDislikeCountDTO(articleLikeRepository.countByStatusLike(LikeStatus.LIKE,articleId), articleLikeRepository.countByStatusLike(LikeStatus.DISLIKE,articleId));
    }


    public ArticleLikeDislikeCountDTO delete(String articleId) {
        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Integer profileId = userDetails.getId();
        articleLikeRepository.deleteByArticleIdAndProfileId(articleId,profileId);
        return new ArticleLikeDislikeCountDTO(articleLikeRepository.countByStatusLike(LikeStatus.LIKE,articleId), articleLikeRepository.countByStatusLike(LikeStatus.DISLIKE,articleId));
    }
}
