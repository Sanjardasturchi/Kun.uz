package com.example.kun_uz_lesson1.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "comment")
public class CommentEntity extends BaseEntity{
    @Column(name = "profile_id")
    private Integer profileId;
    private String content;
    @Column(name = "article_id")
    private String articleId;
    @Column(name = "reply_id")
    private Integer replyId;
    private Boolean visible=true;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id",insertable = false,updatable = false)
    private ProfileEntity profile;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "article_id",insertable = false,updatable = false)
    private ArticleEntity article;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reply_id",insertable = false,updatable = false)
    private CommentEntity replyComment;

    public CommentEntity(String content, String articleId) {
        this.content = content;
        this.articleId = articleId;
    }
}
