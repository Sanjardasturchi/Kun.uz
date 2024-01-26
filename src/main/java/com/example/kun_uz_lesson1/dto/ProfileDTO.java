package com.example.kun_uz_lesson1.dto;

import com.example.kun_uz_lesson1.enums.ProfileRole;
import com.example.kun_uz_lesson1.enums.ProfileStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProfileDTO {
    protected Integer id;
    private String name;
    private String surname;
    private String email;
    private String password;
    private ProfileStatus status;
    private ProfileRole role;
    protected LocalDateTime createdDate;
    protected LocalDateTime updatedDate;
    protected Boolean visible;
    private String jwt;
}
