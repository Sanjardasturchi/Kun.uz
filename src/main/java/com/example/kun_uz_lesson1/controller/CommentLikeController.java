package com.example.kun_uz_lesson1.controller;

import com.example.kun_uz_lesson1.dto.extra.CommentLikeDislikeCountDTO;
import com.example.kun_uz_lesson1.enums.LikeStatus;
import com.example.kun_uz_lesson1.service.CommentLikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

//      9. CommentLike
//    1. Like (ANY)
//        (comment_id)
//    2. Dislike (ANY
//    3. Remove (ANY
@RestController
@RequestMapping("/comment_like")
public class CommentLikeController {
    @Autowired
    private CommentLikeService commentLikeService;

    @PreAuthorize("hasAnyRole('ADMIN','USER','MODERATOR','PUBLISHER')")
    @PostMapping("/like")
    public CommentLikeDislikeCountDTO like(@PathVariable("comment_id") String commentId) {
        return commentLikeService.addStatus(commentId, LikeStatus.LIKE);
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER','MODERATOR','PUBLISHER')")
    @PostMapping("/dislike")
    public CommentLikeDislikeCountDTO dislike(@PathVariable("comment_id") String commentId) {
        return commentLikeService.addStatus(commentId, LikeStatus.DISLIKE);
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER','MODERATOR','PUBLISHER')")
    @DeleteMapping("/delete")
    public CommentLikeDislikeCountDTO delete(@PathVariable("comment_id") String commentId) {
        return commentLikeService.delete(commentId);
    }
}
