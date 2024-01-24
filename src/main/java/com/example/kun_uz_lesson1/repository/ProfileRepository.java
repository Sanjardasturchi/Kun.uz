package com.example.kun_uz_lesson1.repository;

import com.example.kun_uz_lesson1.entity.ProfileEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ProfileRepository extends CrudRepository<ProfileEntity,Integer> {
    Optional<ProfileEntity> findByEmail(String email);
    Optional<ProfileEntity> findByEmailAndPassword(String email,String password);
}
