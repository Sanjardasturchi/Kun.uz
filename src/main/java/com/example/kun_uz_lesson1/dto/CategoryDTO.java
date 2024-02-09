package com.example.kun_uz_lesson1.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CategoryDTO {
    private Integer id;
    private Integer orderNumber;
    @NotBlank(message = "nameUz is empty or null or completed with only spaces")
    private String nameUz;
    @NotBlank(message = "nameRu is empty or null or completed with only spaces")
    private String nameRu;
    @NotBlank(message = "nameEn is empty or null or completed with only spaces")
    private String nameEn;
    private String name;
    private Boolean visible;
    private LocalDateTime createdDate;
}
