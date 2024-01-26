package com.example.kun_uz_lesson1.repository;

import com.example.kun_uz_lesson1.entity.RegionEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface RegionRepository extends CrudRepository<RegionEntity,Integer> {
    @Transactional
    @Modifying
    @Query("update RegionEntity set visible=false where id=?1")
    Integer makeDeleted(Integer id);
    @Query("from RegionEntity where visible=true ")
    Iterable<RegionEntity> all();
    @Transactional
    @Modifying
    @Query("update RegionEntity set nameUz=:nameUz,nameRu=:nameRu,nameEn=:nameEn,orderNumber=:orderNumber where id=:id")
    void updateArticle(Integer id, String nameUz, String nameRu, String nameEn, Integer orderNumber);
}
