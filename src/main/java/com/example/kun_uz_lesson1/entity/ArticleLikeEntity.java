package com.example.kun_uz_lesson1.entity;

import com.example.kun_uz_lesson1.enums.LikeStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "article_like")
public class ArticleLikeEntity extends BaseEntity{
    @Column(name = "profile_id")
    private Integer profileId;
    @Column(name = "article_id")
    private String articleId;
    @Enumerated(EnumType.STRING)
    private LikeStatus status;

    public ArticleLikeEntity(Integer profileId, String articleId, LikeStatus status) {
        this.profileId = profileId;
        this.articleId = articleId;
        this.status = status;
    }
}
