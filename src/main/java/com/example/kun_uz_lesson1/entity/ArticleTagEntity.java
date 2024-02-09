package com.example.kun_uz_lesson1.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
@Setter
@Getter
@Entity
@Table(name = "article_tag")
public class ArticleTagEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String articleId;
    private String tag;
}
