package com.example.kun_uz_lesson1.service;

import com.example.kun_uz_lesson1.dto.AuthDTO;
import com.example.kun_uz_lesson1.dto.ProfileDTO;
import com.example.kun_uz_lesson1.dto.RegistrationDTO;
import com.example.kun_uz_lesson1.entity.ProfileEntity;
import com.example.kun_uz_lesson1.enums.AppLanguage;
import com.example.kun_uz_lesson1.enums.ProfileRole;
import com.example.kun_uz_lesson1.enums.ProfileStatus;
import com.example.kun_uz_lesson1.exp.AppBadException;
import com.example.kun_uz_lesson1.repository.ProfileRepository;
import com.example.kun_uz_lesson1.util.JWTUtil;
import com.example.kun_uz_lesson1.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Locale;
import java.util.Optional;
import java.util.Random;

@Service
public class AuthService {
    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private ResourceBundleMessageSource resourceBundleMessageSource;
    @Autowired
    private ResourceBundleService resourceBundleService;


    public ProfileDTO auth(AuthDTO authDTO, AppLanguage language) {
        Optional<ProfileEntity> entityOptional = profileRepository.findByEmailAndPassword(authDTO.getEmail(), MD5Util.encode(authDTO.getPassword()));
        if (entityOptional.isEmpty()||!entityOptional.get().getVisible()) {
//            String msg=resourceBundleMessageSource.getMessage("email.password.wrong",null,new Locale(language.name()));
            throw new AppBadException(resourceBundleService.getMessage("email.password.wrong",language));
        }

        ProfileEntity entity=entityOptional.get();
        if (!entity.getStatus().equals(ProfileStatus.ACTIVE)) {
            throw new AppBadException("Profile not active");
        }


        ProfileDTO profile=new ProfileDTO();
        profile.setName(entity.getName());
        profile.setSurname(entity.getSurname());
        profile.setRole(entity.getRole());
        profile.setJwt(JWTUtil.encode(entity.getEmail(),entity.getRole()));
        return profile;
    }

//    public void registration(RegistrationDTO registration) {
//        if (registration.getName() == null) {
//            throw new AppBadException("Name is null");
//        }
//        if (registration.getSurname() == null) {
//            throw new AppBadException("Surname is null");
//        }
//        if (registration.getEmail() == null) {
//            throw new AppBadException("Email is null");
//        }
//        if (registration.getPassword() == null) {
//            throw new AppBadException("Password is null");
//        }
//        if (registration.getValidation() == null) {
//            throw new AppBadException("Validation is null");
//        }
//        if (!registration.getPassword().equals(registration.getValidation())){
//            throw new AppBadException("Passwords not matches");
//        }
//        Optional<ProfileEntity> profile = profileRepository.getByEmail(registration.getEmail());
//        if (profile.isPresent()) {
//            ProfileEntity entity = profile.get();
//            if (!entity.getStatus().equals(ProfileStatus.NONACTIVE)) {
//                throw new AppBadException("Profile already exist");
//            }
//        }
//        ProfileEntity entity=new ProfileEntity();
//        entity.setName(registration.getName());
//        entity.setSurname(registration.getSurname());
//        entity.setPassword(MD5Util.encode(registration.getPassword()));
//        entity.setEmail(registration.getEmail());
//        entity.setVisible(true);
//        entity.setRole(ProfileRole.USER);
//        entity.setCreatedDate(LocalDateTime.now());
//        entity.setStatus(ProfileStatus.NONACTIVE);
//        LocalDateTime localDateTime=LocalDateTime.now();
//        entity.setCodeEndTime(localDateTime.plusMinutes(1));
//        Random random = new Random();
//        int code=random.nextInt(1,9);
//        for (int i=0; i<5;i++){
//            code=code*10;
//            code+= random.nextInt(0,9);
//        }
//        entity.setCode(String.valueOf(code));
//
//    }
}
