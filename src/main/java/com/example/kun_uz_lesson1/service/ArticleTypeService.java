package com.example.kun_uz_lesson1.service;

import com.example.kun_uz_lesson1.config.CustomUserDetails;
import com.example.kun_uz_lesson1.dto.ArticleTypeDTO;
import com.example.kun_uz_lesson1.dto.JwtDTO;
import com.example.kun_uz_lesson1.entity.ArticleTypeEntity;
import com.example.kun_uz_lesson1.enums.AppLanguage;
import com.example.kun_uz_lesson1.enums.ProfileRole;
import com.example.kun_uz_lesson1.exp.AppBadException;
import com.example.kun_uz_lesson1.repository.ArticleTypeRepository;
import com.example.kun_uz_lesson1.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class ArticleTypeService {
    @Autowired
    private ArticleTypeRepository articleTypeRepository;
    public ArticleTypeDTO create(ArticleTypeDTO dto) {
        if (dto.getOrderNumber() == null
                || getAll().size() + 1 < dto.getOrderNumber()
                || dto.getOrderNumber() < 1) {
            throw new AppBadException("Wrong order number");
        }
        if (dto.getNameUz()==null||dto.getNameUz().length()<1){
            throw new AppBadException("Wrong order name_uz");
        }
        if (dto.getNameRu()==null||dto.getNameRu().length()<1){
            throw new AppBadException("Wrong order name_ru");
        }

        if (dto.getNameEn()==null||dto.getNameEn().length()<1){
            throw new AppBadException("Wrong order name_en");
        }
        ArticleTypeEntity entity=new ArticleTypeEntity();
        entity.setOrderNumber(dto.getOrderNumber());
        entity.setNameUz(dto.getNameUz());
        entity.setNameRu(dto.getNameRu());
        entity.setNameEn(dto.getNameEn());
        entity.setVisible(true);
        entity.setCreatedDate(LocalDateTime.now());
        articleTypeRepository.save(entity);
        dto.setId(entity.getId());
        dto.setVisible(entity.getVisible());
        dto.setCreatedDate(entity.getCreatedDate());
        return dto;
    }
    public ArticleTypeDTO updateById(Integer id, ArticleTypeDTO dto) {
        Optional<ArticleTypeEntity> optional = articleTypeRepository.findById(id);
        if (optional.isEmpty()) {
            throw new AppBadException("ArticleType not found");
        }
        ArticleTypeEntity entity = optional.get();
        if (dto.getOrderNumber() != null) {
            entity.setOrderNumber(dto.getOrderNumber());
        }else {
            dto.setOrderNumber(entity.getOrderNumber());
        }
        if (dto.getNameUz() != null) {
            entity.setNameUz(dto.getNameUz());
        }else {
            dto.setNameUz(entity.getNameUz());
        }
        if (dto.getNameRu() != null) {
            entity.setNameRu(dto.getNameRu());
        }else {
            dto.setNameRu(entity.getNameRu());
        }
        if (dto.getNameEn() != null) {
            entity.setNameEn(dto.getNameEn());
        }else {
            dto.setNameEn(entity.getNameEn());
        }
        articleTypeRepository.updateArticle(entity.getId(),entity.getNameUz(),entity.getNameRu(),entity.getNameEn(),entity.getOrderNumber());
        dto.setVisible(entity.getVisible());
        dto.setId(entity.getId());
        dto.setCreatedDate(entity.getCreatedDate());
        return dto;
    }
    public String delete(Integer id) {
        if (articleTypeRepository.makeDeleted(id)!=0) {
        return "DONE";
        }
        throw new AppBadException("ArticleType not found");
    }
    public PageImpl<ArticleTypeDTO> allByPagination(Integer page, Integer size) {
        Page<ArticleTypeEntity> all = articleTypeRepository.findAll(PageRequest.of(page-1,size));
        return new PageImpl<>(toDTOListFromIterable(all.getContent()),PageRequest.of(page-1,size),all.getTotalElements());
    }
    public List<ArticleTypeDTO> getByLang(AppLanguage language) {
        Iterable<ArticleTypeEntity> all = articleTypeRepository.all();
        List<ArticleTypeDTO> dtoList=new LinkedList<>();
        for (ArticleTypeEntity entity : all) {
            ArticleTypeDTO dto=new ArticleTypeDTO();
            dto.setId(entity.getId());
            switch (language){
//                case "uz" -> dto.setNameUz(entity.getNameUz());
                case uz -> dto.setName(entity.getNameUz());
                case ru -> dto.setName(entity.getNameRu());
                default -> dto.setName(entity.getNameEn());
            }
            dtoList.add(dto);
        }
        return dtoList;
    }
    public List<ArticleTypeDTO> getAll() {
        return toDTOListFromIterable(articleTypeRepository.all());
    }
    private List<ArticleTypeDTO> toDTOListFromIterable(Iterable<ArticleTypeEntity> entities){
        List<ArticleTypeDTO> dtoList=new LinkedList<>();
        for (ArticleTypeEntity entity : entities) {
            if (entity.getVisible()) {
            dtoList.add(toDTO(entity));
            }
        }
        return dtoList;
    }
    private ArticleTypeDTO toDTO(ArticleTypeEntity entity){
        ArticleTypeDTO dto=new ArticleTypeDTO();
        dto.setId(entity.getId());
        dto.setNameUz(entity.getNameUz());
        dto.setNameRu(entity.getNameRu());
        dto.setNameEn(entity.getNameEn());
        dto.setVisible(entity.getVisible());
        dto.setOrderNumber(entity.getOrderNumber());
        dto.setCreatedDate(entity.getCreatedDate());
        return dto;
    }
}
