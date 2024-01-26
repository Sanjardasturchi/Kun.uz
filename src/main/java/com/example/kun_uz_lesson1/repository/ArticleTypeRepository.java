package com.example.kun_uz_lesson1.repository;

import com.example.kun_uz_lesson1.entity.ArticleTypeEntity;
import com.example.kun_uz_lesson1.entity.RegionEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ArticleTypeRepository extends CrudRepository<ArticleTypeEntity, Integer>, PagingAndSortingRepository<ArticleTypeEntity, Integer> {
    @Transactional
    @Modifying
    @Query("update ArticleTypeEntity set visible=false where id=?1")
    Integer makeDeleted(Integer id);
    @Query("from ArticleTypeEntity where visible=true ")
    Iterable<ArticleTypeEntity> all();

    @Transactional
    @Modifying
    @Query("update ArticleTypeEntity set nameUz=:nameUz,nameRu=:nameRu,nameEn=:nameEn,orderNumber=:orderNumber where id=:id")
    void updateArticle(Integer id, String nameUz, String nameRu, String nameEn, Integer orderNumber);

}
