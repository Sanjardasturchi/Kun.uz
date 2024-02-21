package com.example.kun_uz_lesson1.dto.extra;

import com.example.kun_uz_lesson1.enums.ArticleStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class ArticleFilterDTO {
    private Integer id;
    private String title;
    private Integer regionId;
    private Integer categoryId;
    private LocalDate createdDateFrom;
    private LocalDate createdDateTo;
    private LocalDate publishedDateFrom;
    private LocalDate publishedDateTo;
    private Integer moderatorId;
    private Integer publisherId;
    private ArticleStatus status;
}
