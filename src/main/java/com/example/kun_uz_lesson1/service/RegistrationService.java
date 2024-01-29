package com.example.kun_uz_lesson1.service;

import com.example.kun_uz_lesson1.dto.RegistrationDTO;
import com.example.kun_uz_lesson1.entity.ProfileEntity;
import com.example.kun_uz_lesson1.enums.ProfileRole;
import com.example.kun_uz_lesson1.enums.ProfileStatus;
import com.example.kun_uz_lesson1.exp.AppBadException;
import com.example.kun_uz_lesson1.repository.ProfileRepository;
import com.example.kun_uz_lesson1.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.MimeMessage;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Properties;
import java.util.Random;
@Service
public class RegistrationService {
    @Autowired
    private ProfileRepository profileRepository;

    public static String email="allayarovshahzodbekz@gmail.com";
    public static String PA="kqvmpnebrzwmqowa";
    public String register(RegistrationDTO dto) {
        if (dto.getName() == null) {
            throw new AppBadException("Name is null");
        }
        if (dto.getSurname() == null) {
            throw new AppBadException("Surname is null");
        }
        if (dto.getEmail() == null) {
            throw new AppBadException("Email is null");
        }
        if (dto.getPassword() == null) {
            throw new AppBadException("Password is null");
        }
        if (dto.getValidation() == null) {
            throw new AppBadException("Validation is null");
        }
        if (!dto.getPassword().equals(dto.getValidation())){
            throw new AppBadException("Passwords not matches");
        }
        Optional<ProfileEntity> profile = profileRepository.getByEmail(dto.getEmail());
        if (profile.isPresent()) {
            ProfileEntity entity = profile.get();
            if (!entity.getStatus().equals(ProfileStatus.NONACTIVE)) {
                throw new AppBadException("Profile already exist");
            }
        }
        Random random = new Random();

        int code=random.nextInt(1,9);
        for (int i=0; i<5;i++){
            code=code*10;
            code+= random.nextInt(0,9);
        }
        try {
            sendMail(dto.getEmail(), String.valueOf(code));
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        ProfileEntity entity=new ProfileEntity();
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setPassword(MD5Util.encode(dto.getPassword()));
        entity.setEmail(dto.getEmail());
        entity.setVisible(true);
        entity.setRole(ProfileRole.USER);
        entity.setCreatedDate(LocalDateTime.now());
        entity.setStatus(ProfileStatus.NONACTIVE);
        LocalDateTime localDateTime=LocalDateTime.now();
        entity.setCodeEndTime(localDateTime.plusMinutes(1));
        entity.setCode(String.valueOf(code));
        profileRepository.save(entity);

        return "Enter the code sent to your email";
//
//
//        entity.setCode(String.valueOf(code));
//        Optional<ProfileEntity> EntityEmail = profileRepository.findByEmail(dto.getEmail());
//        if (EntityEmail.isPresent()) {
//            throw new AppBadException("Wrong email");
//        }
//        StringBuilder password = new StringBuilder();
//        for (int i = 0; i < 5; i++) {
//            password.append(random.nextInt(0,9));
//        }
//        try {
//            sendMail(dto.getEmail(), String.valueOf(password));
//        } catch (MessagingException e) {
//            e.printStackTrace();
//        }
//
//
//        ProfileEntity entity = new ProfileEntity();
//        entity.setName(dto.getName());
//        entity.setSurname(dto.getSurname());
//        entity.setPassword(MD5Util.encode(dto.getPassword()));
//        entity.setEmail(dto.getEmail());
//        entity.setRole(ProfileRole.USER);
//        entity.setStatus(ProfileStatus.NONACTIVE);
//        entity.setCreatedDate(LocalDateTime.now());
//        entity.setCodeEndTime(LocalDateTime.now());
//        entity.setCode(String.valueOf(password));
//        profileRepository.save(entity);
//
//        return "Enter the code sent to your email";
    }
    public static void sendMail(String sender, String message) throws MessagingException {
        Session session = getSession(getProperties(), email, PA);
        MimeMessage mimeMessage = new MimeMessage(session);
        mimeMessage.setFrom(sender);
        mimeMessage.setRecipients(Message.RecipientType.TO, sender);
        mimeMessage.setContent(message, "text/plain");
        mimeMessage.setSubject("codeuz");
        Transport.send(mimeMessage);
    }

    public static Session getSession(Properties properties, String email, String password) {
        return Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(email, password);
            }
        });
    }

    public static Properties getProperties() {
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");
        return properties;
    }

}
