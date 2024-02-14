package com.example.kun_uz_lesson1.dto;

import com.example.kun_uz_lesson1.enums.LikeStatus;

import java.time.LocalDateTime;

//id,profile_id,comment_id,created_date,status,
public class CommentLikeDTO {
    private Integer id;
    private Integer profileId;
    private String commentId;
    private LocalDateTime createdDate;
    private LikeStatus status;
}
