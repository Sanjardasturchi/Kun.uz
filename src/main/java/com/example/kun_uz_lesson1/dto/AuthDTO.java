package com.example.kun_uz_lesson1.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AuthDTO {
    @Email(message = "Email not valid",regexp = "^[\\\\w-\\\\.]+@([\\\\w-]+\\\\.)+[\\\\w-]{2,4}$")
    private String email;
    @NotEmpty(message = "Password is empty")
    @NotBlank(message = "Password is empty or null or completed with only spaces")
    private String password;
}
