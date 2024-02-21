package com.example.kun_uz_lesson1.controller;

import com.example.kun_uz_lesson1.dto.extra.ArticleLikeDislikeCountDTO;
import com.example.kun_uz_lesson1.enums.LikeStatus;
import com.example.kun_uz_lesson1.service.ArticleLikeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Tag(name = "ArticleLike API list", description = "API list for ArticleLike")
@RestController
@RequestMapping("/article_like")
public class ArticleLikeController {
    @Autowired
    private ArticleLikeService articleLikeService;
    @PreAuthorize("hasAnyRole('ADMIN','USER','MODERATOR','PUBLISHER')")
    @PostMapping("/like/{article_id}")
    public ArticleLikeDislikeCountDTO like(@PathVariable("article_id") String articleId){
       return articleLikeService.addStatus(articleId, LikeStatus.LIKE);
    }
    @PreAuthorize("hasAnyRole('ADMIN','USER','MODERATOR','PUBLISHER')")
    @PostMapping("/dislike/{article_id}")
    public ArticleLikeDislikeCountDTO dislike(@PathVariable("article_id") String articleId){
       return articleLikeService.addStatus(articleId, LikeStatus.DISLIKE);
    }
    @PreAuthorize("hasAnyRole('ADMIN','USER','MODERATOR','PUBLISHER')")
    @DeleteMapping("/delete/{article_id}")
    public ArticleLikeDislikeCountDTO delete(@PathVariable("article_id") String articleId){
        return articleLikeService.delete(articleId);
    }
}
