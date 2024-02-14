package com.example.kun_uz_lesson1.service;

import com.example.kun_uz_lesson1.config.CustomUserDetails;
import com.example.kun_uz_lesson1.dto.JwtDTO;
import com.example.kun_uz_lesson1.dto.ProfileDTO;
import com.example.kun_uz_lesson1.dto.ProfileFilterDTO;
import com.example.kun_uz_lesson1.entity.ProfileEntity;
import com.example.kun_uz_lesson1.enums.ProfileRole;
import com.example.kun_uz_lesson1.exp.AppBadException;
import com.example.kun_uz_lesson1.repository.ProfileCustomRepository;
import com.example.kun_uz_lesson1.repository.ProfileRepository;
import com.example.kun_uz_lesson1.util.JWTUtil;
import com.example.kun_uz_lesson1.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class ProfileService {
    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private ProfileCustomRepository customRepository;

    public ProfileDTO creat(ProfileDTO dto) {
        ProfileEntity entity = new ProfileEntity();
        if (dto.getName() != null) {
            entity.setName(dto.getName());
        } else {
            throw new AppBadException("Profile name is null!");
        }
        if (dto.getSurname() != null) {
            entity.setSurname(dto.getSurname());
        } else {
            throw new AppBadException("Profile surname is null!");
        }
        if (dto.getEmail() != null) {
            entity.setEmail(dto.getEmail());
        } else {
            throw new AppBadException("Profile email is null!");
        }
        if (dto.getPassword() != null) {
            entity.setPassword(MD5Util.encode(dto.getPassword()));
        } else {
            throw new AppBadException("Profile password is null!");
        }
        if (dto.getStatus() != null) {
            entity.setStatus(dto.getStatus());
        } else {
            throw new AppBadException("Profile status is null!");
        }
        if (dto.getRole() != null) {
            entity.setRole(dto.getRole());
        } else {
            throw new AppBadException("Profile role is null!");
        }
        entity.setCreatedDate(LocalDateTime.now());
        entity.setVisible(true);
        profileRepository.save(entity);
        dto.setId(entity.getId());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setVisible(entity.getVisible());
        return dto;
    }

    public ProfileDTO update(Integer id, ProfileDTO dto) {
        Optional<ProfileEntity> byId = profileRepository.findById(id);
        if (byId.isEmpty()) {
            throw new AppBadException("Profile not found");
        }
        return updateAndSave(dto, byId.get(), ProfileRole.ROLE_ADMIN);
    }

    private ProfileDTO updateAndSave(ProfileDTO dto, ProfileEntity entity, ProfileRole role) {
        if (dto.getName() != null) {
            entity.setName(dto.getName());
        } else {
            dto.setName(entity.getName());
        }
        if (dto.getSurname() != null) {
            entity.setSurname(dto.getSurname());
        } else {
            dto.setSurname(entity.getSurname());
        }
        if (dto.getPassword() != null) {
            entity.setPassword(MD5Util.encode(dto.getPassword()));
        } else {
            dto.setPassword(entity.getPassword());
        }
        if (role.equals(ProfileRole.ROLE_ADMIN)) {
            if (dto.getRole() != null) {
                entity.setRole(dto.getRole());
            }
        } else {
            dto.setRole(entity.getRole());
        }
        if (dto.getEmail() != null) {
            entity.setEmail(dto.getEmail());
        } else {
            dto.setEmail(entity.getEmail());
        }
        if (dto.getRole() != null) {
            entity.setRole(dto.getRole());
        } else {
            dto.setRole(entity.getRole());
        }
        profileRepository.save(entity);
        return dto;
    }

    public ProfileDTO updateOwn(ProfileDTO dto) {
        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<ProfileEntity> byId = profileRepository.findById(userDetails.getId());
        if (byId.isEmpty()) {
            throw new AppBadException("Profile not found");
        }
        return updateAndSave(dto, byId.get(), ProfileRole.ROLE_USER);
    }

    public PageImpl<ProfileDTO> getAll(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<ProfileEntity> all = profileRepository.all(pageable);
        return new PageImpl<>(toDTOList(all.getContent()), PageRequest.of(page - 1, size), all.getTotalElements());
    }

    private List<ProfileDTO> toDTOList(List<ProfileEntity> entities) {
        List<ProfileDTO> dtos = new LinkedList<>();
        for (ProfileEntity entity : entities) {
            dtos.add(toDTO(entity));
        }
        return dtos;
    }

    private ProfileDTO toDTO(ProfileEntity entity) {
        ProfileDTO dto = new ProfileDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setSurname(entity.getSurname());
        dto.setEmail(entity.getEmail());
        dto.setPassword(entity.getPassword());
        dto.setStatus(entity.getStatus());
        dto.setRole(entity.getRole());
        dto.setCreatedDate(entity.getCreatedDate());
        return dto;
    }

    public String delete(Integer id) {
        Optional<ProfileEntity> byId = profileRepository.findById(id);
        if (byId.isEmpty() || (!byId.get().getVisible())) {
            throw new AppBadException("Profile not found");
        }
        profileRepository.makeDeleted(id);
        return "DONE";
    }

    public List<ProfileDTO> filter(ProfileFilterDTO filterDTO) {
        return toDTOList(customRepository.filter(filterDTO));
    }

    public ProfileEntity get(Integer id) {
        Optional<ProfileEntity> byId = profileRepository.findById(id);
        if (byId.isEmpty()) {
            throw new AppBadException("Profile not found");
        }
        return byId.get();
    }
}
