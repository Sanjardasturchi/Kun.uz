package com.example.kun_uz_lesson1.repository;

import com.example.kun_uz_lesson1.entity.ArticleLikeEntity;
import com.example.kun_uz_lesson1.enums.LikeStatus;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ArticleLikeRepository extends CrudRepository<ArticleLikeEntity,Integer> {
    Optional<ArticleLikeEntity> findByArticleIdAndProfileId(String articleId, Integer profileId);
    @Query(value = "select count(a) from article_like a where status=?1 and article_id=?2 ",nativeQuery = true)
    Integer countByStatusLike(LikeStatus status,String articleId);

    @Transactional
    @Modifying
    @Query("update ArticleLikeEntity set status=?1 where articleId=?2 and profileId=?3")
    void updateLikeType(LikeStatus likeStatus, String articleId, Integer profileId);

    void deleteByArticleIdAndProfileId(String articleId, Integer profileId);
}
