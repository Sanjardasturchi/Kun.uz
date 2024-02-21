package com.example.kun_uz_lesson1.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

// id, profile_id, article_id,created_date
@Setter
@Getter
public class SavedArticleDTO {
    private Integer id;
    private Integer profileId;
    private String articleId;
    private LocalDateTime createdDate;
}
