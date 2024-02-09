package com.example.kun_uz_lesson1.repository;

import com.example.kun_uz_lesson1.entity.ArticleTypesEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

public interface ArticleTypesRepository extends CrudRepository<ArticleTypesEntity, Integer>, PagingAndSortingRepository<ArticleTypesEntity, Integer> {
    List<ArticleTypesEntity> findByArticleId(String articleId);

    List<ArticleTypesEntity> findByTypesIdOrderByCreatedDateDesc(Integer typesId);
}
