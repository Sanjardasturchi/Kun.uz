package com.example.kun_uz_lesson1.service;

import com.example.kun_uz_lesson1.dto.EmailHistoryDTO;
import com.example.kun_uz_lesson1.dto.RegistrationDTO;
import com.example.kun_uz_lesson1.entity.EmailHistoryEntity;
import com.example.kun_uz_lesson1.entity.ProfileEntity;
import com.example.kun_uz_lesson1.enums.ProfileRole;
import com.example.kun_uz_lesson1.enums.ProfileStatus;
import com.example.kun_uz_lesson1.exp.AppBadException;
import com.example.kun_uz_lesson1.repository.EmailHistoryRepository;
import com.example.kun_uz_lesson1.repository.ProfileRepository;
import com.example.kun_uz_lesson1.util.JWTUtil;
import com.example.kun_uz_lesson1.util.MD5Util;
import com.example.kun_uz_lesson1.util.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.stereotype.Service;

//import javax.mail.*;
//import javax.mail.internet.MimeMessage;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Properties;
import java.util.Random;
@Service
public class RegistrationService {
    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private EmailHistoryRepository emailHistoryRepository;
    @Autowired
    private EmailHistoryService emailHistoryService;
    @Autowired
    private SMSService smsServerService;
    @Autowired
    private MailSenderService mailSender;


//    public static String email="allayarovshahzodbekz@gmail.com";
//    public static String PA="kqvmpnebrzwmqowa";
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
//        if (dto.getValidation() == null) {
//            throw new AppBadException("Validation is null");
//        }
//        if (!dto.getPassword().equals(dto.getValidation())){
//            throw new AppBadException("Passwords not matches");
//        }
        Optional<ProfileEntity> profile = profileRepository.getByEmail(dto.getEmail());
        if (profile.isPresent()) {
            ProfileEntity entity = profile.get();
            if (!entity.getStatus().equals(ProfileStatus.REGISTRATION)) {
                throw new AppBadException("Profile already exist");
            }
        }
        LocalDateTime from=LocalDateTime.now().minusMinutes(1);
        LocalDateTime to=LocalDateTime.now();
        if (emailHistoryRepository.countSendEmail(dto.getEmail(), from, to) > 3) {
            throw new AppBadException("To many attempt. Please try after 1 minute.");
        }
//        Random random = new Random();
//
//        int code=random.nextInt(1,9);
//        for (int i=0; i<5;i++){
//            code=code*10;
//            code+= random.nextInt(0,9);
//        }

        ProfileEntity entity=new ProfileEntity();
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setPassword(MD5Util.encode(dto.getPassword()));
        entity.setEmail(dto.getEmail());
        entity.setVisible(true);
        entity.setRole(ProfileRole.USER);
        entity.setCreatedDate(LocalDateTime.now());
        entity.setStatus(ProfileStatus.REGISTRATION);
        LocalDateTime localDateTime=LocalDateTime.now();
        entity.setCodeEndTime(localDateTime.plusMinutes(1));
//        entity.setCode(String.valueOf(code));
        profileRepository.save(entity);

        String jwt = JWTUtil.encodeForEmail(entity.getId());
//        String text = "Hello. \n To complete registration please link to the following link\n"
//                + "http://localhost:8080/auth/verification/email/" + jwt;
//        mailSender.sendEmail(dto.getEmail(),"Ok",text);
        String text ="<h1 style=\"text-align: center\">Hello %s</h1>\n" +
                "<p style=\"background-color: indianred; color: white; padding: 30px\">To complete registration please link to the following link</p>\n" +
                "<a style=\" background-color: #f44336;\n" +
                "  color: white;\n" +
                "  padding: 14px 25px;\n" +
                "  text-align: center;\n" +
                "  text-decoration: none;\n" +
                "  display: inline-block;\" href=\"http://localhost:8080/verification/email/%s\n" +
                "\">Click</a>\n" +
                "<br>\n";
        text = String.format(text, entity.getName(), jwt);
        EmailHistoryDTO emailHistoryDTO=new EmailHistoryDTO();
        emailHistoryDTO.setMessage(text);
        emailHistoryDTO.setEmail(entity.getEmail());
        emailHistoryService.create(emailHistoryDTO);
        mailSender.sendEmail(dto.getEmail(), "Complete registration", text);

//        String code = RandomUtil.getRandomSmsCode();
//        smsServerService.send(dto.getPhone(),"KunuzTest verification code: ", code);






        return "Enter the code sent to your email";
    }
//    public static void sendMail(String sender, String message) throws MessagingException {
//        Session session = getSession(getProperties(), email, PA);
//        MimeMessage mimeMessage = new MimeMessage(session);
//        mimeMessage.setFrom(sender);
//        mimeMessage.setRecipients(Message.RecipientType.TO, sender);
//        mimeMessage.setContent(message, "text/plain");
//        mimeMessage.setSubject("codeuz");
//        Transport.send(mimeMessage);
//    }

//    public static Session getSession(Properties properties, String email, String password) {
//        return Session.getInstance(properties, new Authenticator() {
//            @Override
//            protected PasswordAuthentication getPasswordAuthentication() {
//                return new PasswordAuthentication(email, password);
//            }
//        });
//    }

    public static Properties getProperties() {
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");
        return properties;
    }

    public String emailVerification(Integer id) {
        return null;
    }
}
