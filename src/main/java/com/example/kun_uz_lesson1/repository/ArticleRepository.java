package com.example.kun_uz_lesson1.repository;

import com.example.kun_uz_lesson1.dto.ArticleShortInfo;
import com.example.kun_uz_lesson1.entity.ArticleEntity;
import com.example.kun_uz_lesson1.enums.ArticleStatus;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ArticleRepository extends CrudRepository<ArticleEntity, String>, PagingAndSortingRepository<ArticleEntity, String> {
    @Transactional
    @Modifying
    @Query("update ArticleEntity set visible=false where id=?1")
    Integer makeDeleted(String id);
    @Query("from ArticleEntity where visible=true ")
    Iterable<ArticleEntity> all();

    @Query("from ArticleEntity where visible=true order by createdDate desc limit ?1")
    Iterable<ArticleEntity> getLast(int limit);

    @Transactional
    @Modifying
    @Query("update ArticleEntity set status=?1 where id=?2")
    void updateStatus(ArticleStatus status, String id);
    Iterable<ArticleEntity> findByOrderByCreatedDateDesc();

    @Transactional
    @Modifying
    @Query("update ArticleEntity set viewCount=?2 where id=?1")
    void increaseArticleViewCount(String id, int i);

    @Transactional
    @Modifying
    @Query("update ArticleEntity set sharedCount=?2 where id=?1")
    void increaseArticleSharedCount(String id, int i);

    List<ArticleShortInfo> findByOrderByViewCountDesc();

//    @Transactional
//    @Modifying
//    @Query("update ArticleEntity set nameUz=:nameUz,nameRu=:nameRu,nameEn=:nameEn,orderNumber=:orderNumber where id=:id")
//    void updateArticle(Integer id, String nameUz, String nameRu, String nameEn, Integer orderNumber);

}
