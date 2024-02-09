package com.example.kun_uz_lesson1.dto;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ArticleTagDTO {
    private Integer id;
    private String articleId;
    private String tag;
}
