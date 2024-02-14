package com.example.kun_uz_lesson1.entity;

import com.example.kun_uz_lesson1.enums.LikeStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "comment_like")
public class CommentLikeEntity extends BaseEntity{
    private Integer profileId;
    private String commentId;
    private LikeStatus status;

    public CommentLikeEntity() {
    }

    public CommentLikeEntity(Integer profileId, String commentId, LikeStatus status) {
        this.profileId = profileId;
        this.commentId = commentId;
        this.status = status;
    }
}
