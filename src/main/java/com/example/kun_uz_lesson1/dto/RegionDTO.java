package com.example.kun_uz_lesson1.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.time.LocalDateTime;
@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RegionDTO {
    private Integer id;
    @NotNull(message = "OrderNumber required")
    private Integer orderNumber;
    @Size(min = 10,max = 20,message = "NameUz must be between 10 and 20 characters")
    private String nameUz;
    @Size(min = 10,max = 20,message = "NameRu must be between 10 and 20 characters")
    @NotNull(message = "")
    private String nameRu;
    @Size(min = 10,max = 20,message = "NameEn must be between 10 and 20 characters")
    @NotBlank(message = "")
    private String nameEn;
    private String name;
    private Boolean visible;
    private LocalDateTime createdDate;
}
