package com.example.kun_uz_lesson1.repository;

import com.example.kun_uz_lesson1.dto.EmailHistoryDTO;
import com.example.kun_uz_lesson1.entity.EmailHistoryEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface EmailHistoryRepository extends CrudRepository<EmailHistoryEntity,Integer>, PagingAndSortingRepository<EmailHistoryEntity,Integer> {
    @Query("from EmailHistoryEntity where email=?1")
    List<EmailHistoryEntity> findByEmail(String email);
    List<EmailHistoryEntity> findByCreatedDateBetween(LocalDateTime from,LocalDateTime to);

    Long countByEmailAndCreatedDateBetween(String email, LocalDateTime from, LocalDateTime to);

    @Query("SELECT count (s) from EmailSendHistoryEntity s where s.email =?1 and s.createdDate between ?2 and ?3")
    Long countSendEmail(String email, LocalDateTime from, LocalDateTime to);

}
