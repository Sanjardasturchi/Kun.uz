package com.example.kun_uz_lesson1.repository;

import com.example.kun_uz_lesson1.entity.ArticleSavedEntity;
import org.springframework.data.repository.CrudRepository;

public interface ArticleSavedRepository extends CrudRepository<ArticleSavedEntity,Integer> {
    Integer deleteByArticleIdAndProfileId(String articleId, Integer profileId);

    Iterable<ArticleSavedEntity> findByProfileId(Integer profileId);
}
