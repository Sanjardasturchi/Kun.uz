package com.example.kun_uz_lesson1.service;

import com.example.kun_uz_lesson1.dto.EmailHistoryDTO;
import com.example.kun_uz_lesson1.entity.EmailHistoryEntity;
import com.example.kun_uz_lesson1.repository.EmailHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;

@Service
public class EmailHistoryService {//(id, email,message,created_date)
    @Autowired
    private EmailHistoryRepository emailHistoryRepository;

    public EmailHistoryDTO create(EmailHistoryDTO dto) {
        EmailHistoryEntity emailHistoryEntity=new EmailHistoryEntity();
        emailHistoryEntity.setMessage(dto.getMessage());
        emailHistoryEntity.setEmail(dto.getEmail());
        emailHistoryEntity.setCreatedDate(LocalDateTime.now());
        emailHistoryEntity.setVisible(true);
        emailHistoryRepository.save(emailHistoryEntity);
        dto.setCreatedDate(emailHistoryEntity.getCreatedDate());
        return dto;
    }
    public List<EmailHistoryDTO> getByEmail(String email) {
        return toDTOList(emailHistoryRepository.findByEmail(email));
    }

    public List<EmailHistoryDTO> getByGivenDate(LocalDate date) {
        LocalDateTime from=LocalDateTime.of(date, LocalTime.MIN);
        LocalDateTime to=LocalDateTime.of(date, LocalTime.MAX);
        return toDTOList(emailHistoryRepository.findByCreatedDateBetween(from,to));
    }

    public PageImpl<EmailHistoryDTO> getByPagination(Pageable paging) {
        Page<EmailHistoryEntity> all = emailHistoryRepository.findAll(paging);
        return new PageImpl<>(toDTOList(all.getContent()),paging,all.getTotalElements());
    }

    private List<EmailHistoryDTO> toDTOList(List<EmailHistoryEntity> entityList) {
        List<EmailHistoryDTO> dtoList=new LinkedList<>();
        for (EmailHistoryEntity entity : entityList) {
            dtoList.add(toDTO(entity));
        }
        return dtoList;
    }

    private EmailHistoryDTO toDTO(EmailHistoryEntity entity) {
        EmailHistoryDTO dto=new EmailHistoryDTO();
        dto.setId(entity.getId());
        dto.setMessage(entity.getMessage());
        dto.setEmail(entity.getEmail());
        dto.setCreatedDate(entity.getCreatedDate());
        return dto;
    }
}
