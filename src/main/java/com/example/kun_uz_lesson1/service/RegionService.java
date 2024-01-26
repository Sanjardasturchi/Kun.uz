package com.example.kun_uz_lesson1.service;

import com.example.kun_uz_lesson1.dto.ArticleTypeDTO;
import com.example.kun_uz_lesson1.dto.JwtDTO;
import com.example.kun_uz_lesson1.dto.RegionDTO;
import com.example.kun_uz_lesson1.entity.RegionEntity;
import com.example.kun_uz_lesson1.enums.AppLanguage;
import com.example.kun_uz_lesson1.enums.ProfileRole;
import com.example.kun_uz_lesson1.exp.AppBadException;
import com.example.kun_uz_lesson1.repository.RegionRepository;
import com.example.kun_uz_lesson1.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class RegionService {
    @Autowired
    private RegionRepository regionRepository;

    public RegionDTO create(RegionDTO dto,String jwt) {
        JwtDTO decode = JWTUtil.decode(jwt);
        if (!decode.getRole().equals(ProfileRole.ADMIN)) {
            throw new AppBadException("You can not");
        }
        if (dto.getOrderNumber() == null
                || dto.getOrderNumber() < 1) {
            throw new AppBadException("Wrong order number");
        }
        if (dto.getNameUz() == null || dto.getNameUz().length() < 1) {
            throw new AppBadException("Wrong order name_uz");
        }
        if (dto.getNameRu() == null || dto.getNameRu().length() < 1) {
            throw new AppBadException("Wrong order name_ru");
        }

        if (dto.getNameEn() == null || dto.getNameEn().length() < 1) {
            throw new AppBadException("Wrong order name_en");
        }
        RegionEntity entity = new RegionEntity();
        entity.setOrderNumber(dto.getOrderNumber());
        entity.setNameUz(dto.getNameUz());
        entity.setNameRu(dto.getNameRu());
        entity.setNameEn(dto.getNameEn());
        entity.setVisible(true);
        entity.setCreatedDate(LocalDateTime.now());
        regionRepository.save(entity);
        dto.setId(entity.getId());
        dto.setVisible(entity.getVisible());
        dto.setCreatedDate(entity.getCreatedDate());
        return dto;
    }

    public RegionDTO update(Integer id, RegionDTO dto,String jwt) {
        JwtDTO decode = JWTUtil.decode(jwt);
        if (!decode.getRole().equals(ProfileRole.ADMIN)) {
            throw new AppBadException("You can not");
        }
        Optional<RegionEntity> optional = regionRepository.findById(id);
        if (optional.isEmpty()) {
            throw new AppBadException("Region not found");
        }
        RegionEntity entity = optional.get();
        if (dto.getOrderNumber() != null) {
            entity.setOrderNumber(dto.getOrderNumber());
        } else {
            dto.setOrderNumber(entity.getOrderNumber());
        }
        if (dto.getNameUz() != null) {
            entity.setNameUz(dto.getNameUz());
        } else {
            dto.setNameUz(entity.getNameUz());
        }
        if (dto.getNameRu() != null) {
            entity.setNameRu(dto.getNameRu());
        } else {
            dto.setNameRu(entity.getNameRu());
        }
        if (dto.getNameEn() != null) {
            entity.setNameEn(dto.getNameEn());
        } else {
            dto.setNameEn(entity.getNameEn());
        }
        regionRepository.updateArticle(entity.getId(), entity.getNameUz(), entity.getNameRu(), entity.getNameEn(), entity.getOrderNumber());
        dto.setVisible(entity.getVisible());
        dto.setId(entity.getId());
        dto.setCreatedDate(entity.getCreatedDate());
        return dto;
    }

    public String delete(Integer id,String jwt) {
        JwtDTO decode = JWTUtil.decode(jwt);
        if (!decode.getRole().equals(ProfileRole.ADMIN)) {
            throw new AppBadException("You can not");
        }
        if (regionRepository.makeDeleted(id) != 0) {
            return "DONE";
        }
        throw new AppBadException("Region not found");
    }

    public List<RegionDTO> all(String jwt) {
        JwtDTO decode = JWTUtil.decode(jwt);
        if (!decode.getRole().equals(ProfileRole.ADMIN)) {
            throw new AppBadException("You can not");
        }
        return toDTOListFromIterable(regionRepository.all());
    }

    public List<RegionDTO> getByLang(AppLanguage language) {
        Iterable<RegionEntity> all = regionRepository.all();
        List<RegionDTO> dtoList = new LinkedList<>();
        for (RegionEntity entity : all) {
            RegionDTO dto = new RegionDTO();
            dto.setId(entity.getId());
            switch (language) {
                case uz -> dto.setName(entity.getNameUz());
                case ru -> dto.setName(entity.getNameRu());
                default -> dto.setName(entity.getNameEn());
            }
            dtoList.add(dto);
        }
        return dtoList;
    }

    private List<RegionDTO> toDTOListFromIterable(Iterable<RegionEntity> entities) {
        List<RegionDTO> dtoList = new LinkedList<>();
        for (RegionEntity entity : entities) {
            if (entity.getVisible()) {
                dtoList.add(toDTO(entity));
            }
        }
        return dtoList;
    }

    private RegionDTO toDTO(RegionEntity entity) {
        RegionDTO dto = new RegionDTO();
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
