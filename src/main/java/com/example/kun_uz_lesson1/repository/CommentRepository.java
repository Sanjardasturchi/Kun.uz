package com.example.kun_uz_lesson1.repository;

import com.example.kun_uz_lesson1.entity.CommentEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends CrudRepository<CommentEntity,Integer>, PagingAndSortingRepository<CommentEntity,Integer> {

    @Query("from CommentEntity where visible=true and articleId=?1")
    PageImpl<CommentEntity> findByArticleId(String articleId, Pageable pageable);
    @Query("from CommentEntity where visible=true ")
    List<CommentEntity> findByArticleId(String articleId);

    @Transactional
    @Modifying
    @Query("update CommentEntity set visible=false where articleId=?1 and id=?2")
    void deleteByArticleId(String articleId, Integer commentId);

    Optional<CommentEntity> findByIdAndArticleId(Integer commentId, String articleId);

    List<CommentEntity> findByReplyId(Integer commentId);
}
