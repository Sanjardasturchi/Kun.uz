package com.example.kun_uz_lesson1.repository;

import com.example.kun_uz_lesson1.entity.ArticleTagEntity;
import com.example.kun_uz_lesson1.entity.ArticleTypesEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ArticleTagRepository extends CrudRepository<ArticleTagEntity, Integer> {
     List<ArticleTagEntity> findByTag(String tag);
}
