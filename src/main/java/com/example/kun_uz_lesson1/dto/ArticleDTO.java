package com.example.kun_uz_lesson1.dto;

import com.example.kun_uz_lesson1.enums.ArticleStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

//id(uuid),title,description,content,shared_count,image_id,
//    region_id,category_id,moderator_id,publisher_id,status(Published,NotPublished)
//    created_date,published_date,visible,view_count
//    (Bitta article bir-nechta type da bo'lishi mumkun. Masalan Asosiy,Muharrir da.)
@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ArticleDTO {
    private String id;
    private String title;
    private String description;
    private String content;
    private Integer sharedCount;
    private String photoId;
    private LocalDateTime createdDate;
    private LocalDateTime publishedDate;
    private Boolean visible;
    private Integer viewCount;
    private ArticleStatus status;

    private Integer[] articleTypeId;
    private List<Integer> articleType;
    private Integer regionId;
    private Integer categoryId;
    private Integer moderatorId;
    private Integer publisherId;

    public ArticleDTO() {
    }

    public ArticleDTO(String id, String title) {
        this.id = id;
        this.title = title;
    }
}
