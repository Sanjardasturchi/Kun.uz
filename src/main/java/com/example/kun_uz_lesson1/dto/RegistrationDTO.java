package com.example.kun_uz_lesson1.dto;

import lombok.Getter;
import lombok.Setter;

//(name,surname,login/email/phone,password) + validation. password with MD5 encode
@Setter
@Getter
public class RegistrationDTO {
    private String name;
    private String surname;
    private String email;
    private String password;
    private String validation;
}
