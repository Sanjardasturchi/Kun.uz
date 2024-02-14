package com.example.kun_uz_lesson1.dto.extra;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentUpdateDTO {
    Integer commentId;
    String articleId;
    String content;
}
