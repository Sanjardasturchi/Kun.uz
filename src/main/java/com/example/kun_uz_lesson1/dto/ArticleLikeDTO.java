package com.example.kun_uz_lesson1.dto;

import com.example.kun_uz_lesson1.enums.LikeStatus;

import java.time.LocalDateTime;

//id,profile_id,article_id,created_date,status,
public class ArticleLikeDTO {
    private Integer id;
    private Integer profileId;
    private String articleId;
    private LocalDateTime createdDate;
    private LikeStatus status;
}
