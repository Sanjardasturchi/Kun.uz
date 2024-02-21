package com.example.kun_uz_lesson1.service;

import com.example.kun_uz_lesson1.dto.ArticleTagDTO;
import com.example.kun_uz_lesson1.entity.ArticleTagEntity;
import com.example.kun_uz_lesson1.repository.ArticleTagRepository;
import com.example.kun_uz_lesson1.repository.ArticleTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class ArticleTagService {
    @Autowired
    private ArticleTagRepository articleTagRepository;
    public List<ArticleTagDTO> getAllByTag(String tag){
        return toDTOList(articleTagRepository.findByTag(tag));

    }
    private List<ArticleTagDTO> toDTOList(List<ArticleTagEntity> entityList){
        List<ArticleTagDTO> dtoList = new LinkedList<>();
        for (ArticleTagEntity articleTagEntity : entityList) {
            dtoList.add(toDTO(articleTagEntity));
        }
        return dtoList;
    }
    private ArticleTagDTO toDTO(ArticleTagEntity entity){
        ArticleTagDTO dto = new ArticleTagDTO();
        dto.setId(entity.getId());
        dto.setArticleId(entity.getArticleId());
        dto.setTag(entity.getTag());
        return dto;
    }
}
