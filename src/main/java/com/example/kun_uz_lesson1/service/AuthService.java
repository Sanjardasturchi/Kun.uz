package com.example.kun_uz_lesson1.service;

import com.example.kun_uz_lesson1.dto.AuthDTO;
import com.example.kun_uz_lesson1.dto.ProfileDTO;
import com.example.kun_uz_lesson1.entity.ProfileEntity;
import com.example.kun_uz_lesson1.exp.AppBadException;
import com.example.kun_uz_lesson1.repository.ProfileRepository;
import com.example.kun_uz_lesson1.util.JWTUtil;
import com.example.kun_uz_lesson1.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    @Autowired
    private ProfileRepository profileRepository;

    public ProfileDTO auth(AuthDTO authDTO) {
        Optional<ProfileEntity> entityOptional = profileRepository.findByEmailAndPassword(authDTO.getEmail(), MD5Util.encode(authDTO.getPassword()));
        if (entityOptional.isEmpty()) {
            throw new AppBadException("Email or Password s wrong!");
        }
        ProfileEntity entity=entityOptional.get();
        ProfileDTO profile=new ProfileDTO();
        profile.setName(entity.getName());
        profile.setSurname(entity.getSurname());
        profile.setRole(entity.getRole());
        profile.setJwt(JWTUtil.encode(entity.getId(),entity.getRole()));
        return profile;
    }
}
