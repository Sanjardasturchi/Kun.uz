package com.example.kun_uz_lesson1.dto.extra;

import com.example.kun_uz_lesson1.dto.ArticleTypeDTO;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class ArticleCreateDTO {
    @NotBlank(message = "TitleUz is empty or null or completed with only spaces")
    private String titleUz;
    @NotBlank(message = "TitleRu is empty or null or completed with only spaces")
    private String titleRu;
    @NotBlank(message = "TitleEn is empty or null or completed with only spaces")
    private String titleEn;
    @NotBlank(message = "DescriptionUz is empty or null or completed with only spaces")
    private String descriptionUz;
    @NotBlank(message = "DescriptionRu is empty or null or completed with only spaces")
    private String descriptionRu;
    @NotBlank(message = "DescriptionEn is empty or null or completed with only spaces")
    private String descriptionEn;
    @NotBlank(message = "ContentUz is empty or null or completed with only spaces")
    private String contentUz;
    @NotBlank(message = "ContentRU is empty or null or completed with only spaces")
    private String contentRu;
    @NotBlank(message = "ContentEn is empty or null or completed with only spaces")
    private String contentEn;
    private String photoId;
    private List<Integer> articleType;
    private Integer regionId;
    private Integer categoryId;
    private List<ArticleTypeDTO> articleTypeList;
}
