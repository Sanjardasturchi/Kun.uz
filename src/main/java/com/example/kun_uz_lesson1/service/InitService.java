package com.example.kun_uz_lesson1.service;

import com.example.kun_uz_lesson1.entity.ProfileEntity;
import com.example.kun_uz_lesson1.enums.ProfileRole;
import com.example.kun_uz_lesson1.enums.ProfileStatus;
import com.example.kun_uz_lesson1.repository.ProfileRepository;
import com.example.kun_uz_lesson1.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InitService {
    @Autowired
    private ProfileRepository profileRepository;
    public void initAdmin() {
        String adminEmail = "admin@mail.ru";
        Optional<ProfileEntity> optional = profileRepository.findByEmail(adminEmail);
        if (optional.isPresent()) {
            return;
        }
        // create admin
        ProfileEntity admin = new ProfileEntity();
        admin.setName("Admin");
        admin.setSurname("Adminjon");
        admin.setEmail(adminEmail);
        admin.setStatus(ProfileStatus.ACTIVE);
        admin.setRole(ProfileRole.ADMIN);
        admin.setPassword(MD5Util.encode("12345"));
        profileRepository.save(admin);
    }

}
