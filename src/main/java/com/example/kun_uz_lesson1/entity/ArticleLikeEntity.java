package com.example.kun_uz_lesson1.entity;

import com.example.kun_uz_lesson1.enums.LikeStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "article_like")
public class ArticleLikeEntity extends BaseEntity{
    @Column(name = "profile_id")
    private Integer profileId;
    @Column(name = "article_id")
    private String articleId;
    private LikeStatus status;
}
