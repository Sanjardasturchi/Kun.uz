package com.example.kun_uz_lesson1.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Table(name = "article_saved")
public class ArticleSavedEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;
    @Column(name = "profile_id")
    private Integer profileId;
    @Column(name = "article_id")
    private String articleId;
    @Column(name = "created_date")
    protected LocalDateTime createdDate = LocalDateTime.now();
}
