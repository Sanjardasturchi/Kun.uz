package com.example.kun_uz_lesson1.dto.extra;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CommentLikeDislikeCountDTO {
    private Integer likeCount;
    private Integer dislikeCount;

    public CommentLikeDislikeCountDTO() {
    }

    public CommentLikeDislikeCountDTO(Integer likeCount, Integer dislikeCount) {
        this.likeCount = likeCount;
        this.dislikeCount = dislikeCount;
    }
}
