package com.example.kun_uz_lesson1.controller;

import com.example.kun_uz_lesson1.dto.SavedArticleDTO;
import com.example.kun_uz_lesson1.dto.extra.ArticleSavedDTO;
import com.example.kun_uz_lesson1.service.ArticleSavedService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "ArticleSaved API list", description = "API list for ArticleSaved")
@RestController
@RequestMapping("/article_saved")
public class ArticleSavedController {
    @Autowired
    private ArticleSavedService articleSavedService;
    @PreAuthorize("hasAnyRole('ADMIN','USER','MODERATOR','PUBLISHER')")
    @PostMapping("/creat/{id}")
    public String creat(@PathVariable("id")String articleId){
        return articleSavedService.creat(articleId);
    }
    @PreAuthorize("hasAnyRole('ADMIN','USER','MODERATOR','PUBLISHER')")
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id")String articleId){
        return articleSavedService.delete(articleId);
    }
    @PreAuthorize("hasAnyRole('ADMIN','USER','MODERATOR','PUBLISHER')")
    @PostMapping("/getList")
    public List<ArticleSavedDTO> getList(){
        return articleSavedService.getList();
    }
}
