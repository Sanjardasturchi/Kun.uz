package com.example.kun_uz_lesson1.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RegistrationDTO {
    @NotBlank(message = "name is empty or null or completed with only spaces")
    private String name;
    @NotBlank(message = "surname is empty or null or completed with only spaces")
    private String surname;
    @Email(message = "Email not valid",regexp = "^[\\\\w-\\\\.]+@([\\\\w-]+\\\\.)+[\\\\w-]{2,4}$")
    private String email;
    @NotBlank(message = "phone is empty or null or completed with only spaces")
    private String phone;
    @NotBlank(message = "password is empty or null or completed with only spaces")
    private String password;
    private String validation;
}
