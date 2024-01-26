package com.example.kun_uz_lesson1.repository;

import com.example.kun_uz_lesson1.dto.ProfileFilterDTO;
import com.example.kun_uz_lesson1.entity.ProfileEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Repository
public class ProfileCustomRepository {
    @Autowired
    private EntityManager entityManager;
    public List<ProfileEntity> filter(ProfileFilterDTO filter) {
        StringBuilder builder = new StringBuilder();
        Map<String, Object> params = new HashMap<>();
        if (filter.getRole() != null) {
            builder.append("and role =:role ");
            params.put("role", filter.getRole());
        }
        if (filter.getName() != null) {
            builder.append("and lower(name) like :name ");
            params.put("name", filter.getName());
        }
        if (filter.getSurname() != null) {
            builder.append("and lower(surname) like :surname ");
            params.put("surname", "%" + filter.getSurname().toLowerCase() + "%");
        }
        if (filter.getFromDate() != null && filter.getToDate() != null) {
            LocalDateTime fromDate = LocalDateTime.of(filter.getFromDate(), LocalTime.MIN);
            LocalDateTime toDate = LocalDateTime.of(filter.getToDate(), LocalTime.MAX);
            builder.append("and createdDate between :fromDate and :toDate ");
            params.put("fromDate", fromDate);
            params.put("toDate", toDate);
        } else if (filter.getFromDate() != null) {
            LocalDateTime fromDate = LocalDateTime.of(filter.getFromDate(), LocalTime.MIN);
            LocalDateTime toDate = LocalDateTime.of(filter.getFromDate(), LocalTime.MAX);
            builder.append("and createdDate between :fromDate and :toDate ");
            params.put("fromDate", fromDate);
            params.put("toDate", toDate);
        } else if (filter.getToDate() != null) {
            LocalDateTime toDate = LocalDateTime.of(filter.getToDate(), LocalTime.MAX);
            builder.append("and createdDate <= :toDate ");
            params.put("toDate", toDate);
        }

        StringBuilder selectBuilder = new StringBuilder("FROM ProfileEntity s where visible=true     ");
        selectBuilder.append(builder);
//        selectBuilder.append(" order by createdDate desc ");


        Query selectQuery = entityManager.createQuery(selectBuilder.toString());

        for (Map.Entry<String, Object> param : params.entrySet()) {
            selectQuery.setParameter(param.getKey(), param.getValue());
        }
        List<ProfileEntity> entityList = selectQuery.getResultList();
        return entityList;
    }
}
