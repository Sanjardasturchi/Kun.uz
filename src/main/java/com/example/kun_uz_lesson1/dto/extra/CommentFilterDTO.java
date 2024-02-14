package com.example.kun_uz_lesson1.dto.extra;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class CommentFilterDTO {
    private Integer id;
    private LocalDate fromDate;
    private LocalDate toDate;
    private Integer profileId;
    private String articleId;
}
