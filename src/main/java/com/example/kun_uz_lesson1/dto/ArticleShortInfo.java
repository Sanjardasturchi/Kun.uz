package com.example.kun_uz_lesson1.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class ArticleShortInfo {
    private String id;
    private String title;
    private String description;
    private AttachDTO photo;
    private LocalDateTime publishedDate;
}
