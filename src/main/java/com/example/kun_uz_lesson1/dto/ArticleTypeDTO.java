package com.example.kun_uz_lesson1.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ArticleTypeDTO {
//       id,order_number,name_uz, name_ru, name_en,visible,created_date
//     (Asosiy,Muharrir tanlovi,Dolzarb, Maqola,
//        Foto yangilik,Interview,Biznes,Surushturuv,Video Yangilik)
    private Integer id;
    private Integer orderNumber;
    private String nameUz;
    private String nameRu;
    private String nameEn;
    private String name;
    private Boolean visible;
    private LocalDateTime createdDate;

}
