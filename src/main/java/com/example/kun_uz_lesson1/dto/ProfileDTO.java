package com.example.kun_uz_lesson1.dto;

import com.example.kun_uz_lesson1.enums.ProfileRole;
import com.example.kun_uz_lesson1.enums.ProfileStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProfileDTO {
    protected Integer id;
    @NotBlank(message = "name is empty or null or completed with only spaces")
    private String name;
    @NotBlank(message = "surname is empty or null or completed with only spaces")
    private String surname;
    @Email(message = "Email not valid",regexp = "^[\\\\w-\\\\.]+@([\\\\w-]+\\\\.)+[\\\\w-]{2,4}$")
    private String email;
    @NotBlank(message = "Password is empty or null or completed with only spaces")
    private String password;
    private ProfileStatus status;
    private ProfileRole role;
    protected LocalDateTime createdDate;
    protected LocalDateTime updatedDate;
    protected Boolean visible;
    private String jwt;
}
