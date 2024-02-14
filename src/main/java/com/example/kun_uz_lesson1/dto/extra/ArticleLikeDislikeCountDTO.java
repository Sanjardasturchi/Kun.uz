package com.example.kun_uz_lesson1.dto.extra;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ArticleLikeDislikeCountDTO {
    private Integer likeCount;
    private Integer dislikeCount;

    public ArticleLikeDislikeCountDTO() {
    }

    public ArticleLikeDislikeCountDTO(Integer likeCount, Integer dislikeCount) {
        this.likeCount = likeCount;
        this.dislikeCount = dislikeCount;
    }
}
