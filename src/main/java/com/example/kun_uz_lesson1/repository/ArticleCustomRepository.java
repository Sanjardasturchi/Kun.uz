package com.example.kun_uz_lesson1.repository;

import com.example.kun_uz_lesson1.dto.extra.ArticleFilterDTO;
import com.example.kun_uz_lesson1.entity.ArticleEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ArticleCustomRepository {
    @Autowired
    private EntityManager entityManager;

    public PageImpl<ArticleEntity> filter(ArticleFilterDTO filter, Integer size, Integer page) {
        StringBuilder builder = new StringBuilder();
        Map<String, Object> params = new HashMap<>();
        if (filter.getId() != null) {
            builder.append("and id =:id ");
            params.put("id", filter.getId());
        }
        if (filter.getModeratorId() != null) {
            builder.append("and moderatorId =:moderatorId ");
            params.put("moderatorId", filter.getModeratorId());
        }
        if (filter.getPublisherId() != null) {
            builder.append("and publisherId =:publisherId ");
            params.put("publisherId", filter.getPublisherId());
        }
        if (filter.getCategoryId() != null) {
            builder.append("and categoryId =:categoryId ");
            params.put("categoryId", filter.getCategoryId());
        }
        if (filter.getRegionId() != null) {
            builder.append("and regionId =:regionId ");
            params.put("regionId", filter.getRegionId());
        }
        if (filter.getStatus() != null) {
            builder.append("and status =:status ");
            params.put("status", filter.getStatus());
        }
//        if (filter.getTitle() != null) {
//            builder.append("and title =:title ");
//            params.put("title", filter.getTitle());
//        }


        if (filter.getCreatedDateFrom() != null && filter.getCreatedDateTo() != null) {
            LocalDateTime fromDate = LocalDateTime.of(filter.getCreatedDateFrom(), LocalTime.MIN);
            LocalDateTime toDate = LocalDateTime.of(filter.getCreatedDateTo(), LocalTime.MAX);
            builder.append("and createdDate between :fromDate and :toDate ");
            params.put("fromDate", fromDate);
            params.put("toDate", toDate);
        } else if (filter.getCreatedDateFrom() != null) {
            LocalDateTime fromDate = LocalDateTime.of(filter.getCreatedDateFrom(), LocalTime.MIN);
            LocalDateTime toDate = LocalDateTime.of(filter.getCreatedDateFrom(), LocalTime.MAX);
            builder.append("and createdDate between :fromDate and :toDate ");
            params.put("fromDate", fromDate);
            params.put("toDate", toDate);
        } else if (filter.getCreatedDateTo() != null) {
            LocalDateTime toDate = LocalDateTime.of(filter.getCreatedDateTo(), LocalTime.MAX);
            builder.append("and createdDate <= :toDate ");
            params.put("toDate", toDate);
        }
        if (filter.getPublishedDateFrom() != null && filter.getPublishedDateTo() != null) {
            LocalDateTime fromDate = LocalDateTime.of(filter.getPublishedDateFrom(), LocalTime.MIN);
            LocalDateTime toDate = LocalDateTime.of(filter.getPublishedDateTo(), LocalTime.MAX);
            builder.append("and publishedDate between :fromDate and :toDate ");
            params.put("fromDate", fromDate);
            params.put("toDate", toDate);
        } else if (filter.getPublishedDateFrom() != null) {
            LocalDateTime fromDate = LocalDateTime.of(filter.getPublishedDateFrom(), LocalTime.MIN);
            LocalDateTime toDate = LocalDateTime.of(filter.getPublishedDateFrom(), LocalTime.MAX);
            builder.append("and publishedDate between :fromDate and :toDate ");
            params.put("fromDate", fromDate);
            params.put("toDate", toDate);
        } else if (filter.getPublishedDateTo() != null) {
            LocalDateTime toDate = LocalDateTime.of(filter.getPublishedDateTo(), LocalTime.MAX);
            builder.append("and publishedDate <= :toDate ");
            params.put("toDate", toDate);
        }
        StringBuilder selectBuilder = new StringBuilder("FROM ArticleEntity s where visible =true ");
        selectBuilder.append(builder);
//        selectBuilder.append(" order by createdDate desc ");

        StringBuilder countBuilder = new StringBuilder("Select count(s) FROM ArticleEntity s where visible =true ");
        countBuilder.append(builder);

        Query selectQuery = entityManager.createQuery(selectBuilder.toString());
        selectQuery.setMaxResults(size); // limit
        selectQuery.setFirstResult((page - 1) * size); // offset (page-1)*size
        Query countQuery = entityManager.createQuery(countBuilder.toString());

        for (Map.Entry<String, Object> param : params.entrySet()) {
            selectQuery.setParameter(param.getKey(), param.getValue());
            countQuery.setParameter(param.getKey(), param.getValue());
        }
        List<ArticleEntity> entityList = selectQuery.getResultList();
        Long totalElements = (Long) countQuery.getSingleResult();
        return new PageImpl<ArticleEntity>(entityList, PageRequest.of(page-1,size),totalElements);
    }
}
