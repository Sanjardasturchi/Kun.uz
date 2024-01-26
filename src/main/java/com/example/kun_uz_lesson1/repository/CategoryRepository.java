package com.example.kun_uz_lesson1.repository;

import com.example.kun_uz_lesson1.entity.CategoryEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CategoryRepository extends CrudRepository<CategoryEntity,Integer>, PagingAndSortingRepository<CategoryEntity,Integer> {
    @Transactional
    @Modifying
    @Query("update CategoryEntity set visible=false where id=?1")
    Integer makeDeleted(Integer id);
    @Query("from CategoryEntity where visible=true order by orderNumber desc ")
    Iterable<CategoryEntity > all();
    @Transactional
    @Modifying
    @Query("update CategoryEntity set nameUz=:nameUz,nameRu=:nameRu,nameEn=:nameEn,orderNumber=:orderNumber where id=:id")
    void updateArticle(Integer id, String nameUz, String nameRu, String nameEn, Integer orderNumber);
}
