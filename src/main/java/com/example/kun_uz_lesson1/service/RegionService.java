package com.example.kun_uz_lesson1.service;

import com.example.kun_uz_lesson1.dto.RegionDTO;
import com.example.kun_uz_lesson1.entity.RegionEntity;
import com.example.kun_uz_lesson1.exp.AppBadException;
import com.example.kun_uz_lesson1.repository.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class RegionService {
    @Autowired
    private RegionRepository regionRepository;
    public RegionDTO create(RegionDTO dto) {
        if (dto.getOrderNumber() == null
                || all().size() + 1 < dto.getOrderNumber()
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
        RegionEntity entity=new RegionEntity();
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

    public RegionDTO update(Integer id, RegionDTO dto) {
        Optional<RegionEntity> optional = regionRepository.findById(id);
        if (optional.isEmpty()) {
            throw new AppBadException("Region not found");
        }
        RegionEntity entity = optional.get();
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
        regionRepository.save(entity);
        dto.setVisible(entity.getVisible());
        dto.setId(entity.getId());
        dto.setCreatedDate(entity.getCreatedDate());
        return dto;    }

    public String delete(Integer id) {
        return null;
    }

    public List<RegionDTO> all() {
        return null;
    }

    public List<RegionDTO> getByLang(String language) {
        return null;
    }
}
