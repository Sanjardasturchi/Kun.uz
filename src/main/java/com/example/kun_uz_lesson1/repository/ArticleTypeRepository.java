package com.example.kun_uz_lesson1.repository;

import com.example.kun_uz_lesson1.entity.ArticleTypeEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ArticleTypeRepository extends CrudRepository<ArticleTypeEntity,Integer>, PagingAndSortingRepository<ArticleTypeEntity,Integer> {

}
