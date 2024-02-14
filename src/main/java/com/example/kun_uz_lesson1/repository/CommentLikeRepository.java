package com.example.kun_uz_lesson1.repository;

import com.example.kun_uz_lesson1.entity.CommentLikeEntity;
import com.example.kun_uz_lesson1.enums.LikeStatus;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CommentLikeRepository extends CrudRepository<CommentLikeEntity,Integer> {
    Optional<CommentLikeEntity > findByCommentIdAndProfileId(String commentId, Integer profileId);
    @Query(value = "select count(a) from comment_like a where status=?1",nativeQuery = true)
    Integer countByStatusLike(LikeStatus status);

    @Transactional
    @Modifying
    @Query("update CommentLikeEntity set status=?1 where commentId=?2 and profileId=?3")
    void updateLikeType(LikeStatus likeStatus, String commentId, Integer profileId);

    void deleteByCommentIdAndProfileId(String commentId, Integer profileId);
}
