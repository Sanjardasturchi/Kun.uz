package com.example.kun_uz_lesson1.service;

import com.example.kun_uz_lesson1.config.CustomUserDetails;
import com.example.kun_uz_lesson1.dto.ArticleShortInfo;
import com.example.kun_uz_lesson1.dto.SavedArticleDTO;
import com.example.kun_uz_lesson1.dto.extra.ArticleSavedDTO;
import com.example.kun_uz_lesson1.entity.ArticleEntity;
import com.example.kun_uz_lesson1.entity.ArticleSavedEntity;
import com.example.kun_uz_lesson1.repository.ArticleRepository;
import com.example.kun_uz_lesson1.repository.ArticleSavedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class ArticleSavedService {
    @Autowired
    private ArticleSavedRepository articleSavedRepository;
    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private ArticleService articleService;

    public String creat(String articleId) {
        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Integer profileId = userDetails.getId();
        ArticleSavedEntity articleSaved=new ArticleSavedEntity();
        articleSaved.setArticleId(articleId);
        articleSaved.setProfileId(profileId);
        articleSaved.setCreatedDate(LocalDateTime.now());
        articleSavedRepository.save(articleSaved);
        return "DONE";
    }

    public String delete(String articleId) {
        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Integer profileId = userDetails.getId();
        Integer effectRows=articleSavedRepository.deleteByArticleIdAndProfileId(articleId,profileId);
        if (effectRows==0) {
            return "Not found";
        }
        return "DONE";
    }

    public List<ArticleSavedDTO> getList() {
        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Integer profileId = userDetails.getId();
        Iterable<ArticleSavedEntity> list=articleSavedRepository.findByProfileId(profileId);
        List<ArticleSavedDTO> dtoList=new LinkedList<>();
        for (ArticleSavedEntity articleSaved : list) {
            ArticleSavedDTO dto=new ArticleSavedDTO();
            dto.setId(articleSaved.getId());
            Optional<ArticleEntity> optional = articleRepository.findById(articleSaved.getArticleId());
            if (optional.isEmpty()) continue;
            dto.setArticle(articleService.toShortDTO(optional.get()));
            dtoList.add(dto);
        }
        return dtoList;
    }
}
