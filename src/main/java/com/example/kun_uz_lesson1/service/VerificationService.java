package com.example.kun_uz_lesson1.service;

import com.example.kun_uz_lesson1.dto.JwtDTO;
import com.example.kun_uz_lesson1.dto.ProfileDTO;
import com.example.kun_uz_lesson1.dto.VerificationDTO;
import com.example.kun_uz_lesson1.entity.ProfileEntity;
import com.example.kun_uz_lesson1.enums.ProfileStatus;
import com.example.kun_uz_lesson1.exp.AppBadException;
import com.example.kun_uz_lesson1.repository.ProfileRepository;
import com.example.kun_uz_lesson1.util.JWTUtil;
import io.jsonwebtoken.JwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class VerificationService {
    @Autowired
    private ProfileRepository profileRepository;
    public ProfileDTO verification(VerificationDTO verificationDTO) {
        Optional<ProfileEntity> profile = profileRepository.findByEmail(verificationDTO.getEmail());
        if (profile.isEmpty()) {
            throw new AppBadException("Profile not found");
        }
        ProfileEntity entity = profile.get();
        if (!entity.getStatus().equals(ProfileStatus.REGISTRATION)) {
            throw  new AppBadException("!ProfileStatus.REGISTRATION");
        }
        if (entity.getCodeEndTime().isBefore(LocalDateTime.now())) {
            throw new AppBadException("Verification time out");
        }
        if (!entity.getCode().equals(verificationDTO.getCode())) {
            throw new AppBadException("Code not matches");
        }
        entity.setStatus(ProfileStatus.ACTIVE);
        return toDTO(entity);
    }
    public String emailVerification(String jwt) {
        try {
            JwtDTO jwtDTO = JWTUtil.decode(jwt);

            Optional<ProfileEntity> optional = profileRepository.findById(jwtDTO.getId());
            if (!optional.isPresent()) {
                throw new AppBadException("Profile not found");
            }
            ProfileEntity entity = optional.get();
            if (!entity.getStatus().equals(ProfileStatus.REGISTRATION)) {
                throw new AppBadException("Profile in wrong status");
            }
            profileRepository.updateStatus(entity.getId(), ProfileStatus.ACTIVE);
        } catch (JwtException e) {
            throw new AppBadException("Please tyre again.");
        }
        return null;
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
        dto.setJwt(JWTUtil.encode(entity.getId(),entity.getRole()));
        return dto;
    }
}
