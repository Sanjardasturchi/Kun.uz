package com.example.kun_uz_lesson1.dto.extra;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CreatCommentDTO {
    @NotBlank(message = "Content can not be empty")
    private String content;
    @NotBlank(message = "ArticleId can not be empty")
    private String articleId;
    private Integer replyId;
}
