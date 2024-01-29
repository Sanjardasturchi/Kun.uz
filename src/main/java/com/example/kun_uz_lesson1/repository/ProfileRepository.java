package com.example.kun_uz_lesson1.repository;

import com.example.kun_uz_lesson1.entity.ProfileEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface ProfileRepository extends CrudRepository<ProfileEntity,Integer>, PagingAndSortingRepository<ProfileEntity,Integer> {
    Optional<ProfileEntity> findByEmail(String email);
    Optional<ProfileEntity> findByEmailAndPassword(String email,String password);

    @Transactional
    @Modifying
    @Query("update ProfileEntity set visible=false where id=?1")
    Integer makeDeleted(Integer id);
    @Query("from ProfileEntity where visible=true ")
    Page<ProfileEntity> all(Pageable pageable);

    Optional<ProfileEntity> getByEmail(String email);
}
