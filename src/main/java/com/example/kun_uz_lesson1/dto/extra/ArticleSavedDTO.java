package com.example.kun_uz_lesson1.dto.extra;

import com.example.kun_uz_lesson1.dto.ArticleDTO;
import com.example.kun_uz_lesson1.dto.ArticleShortInfo;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ArticleSavedDTO {
    private Integer id;
    private ArticleShortInfo article;
}
