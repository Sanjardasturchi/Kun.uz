package com.example.kun_uz_lesson1.dto.extra;

import com.example.kun_uz_lesson1.dto.CategoryDTO;
import com.example.kun_uz_lesson1.dto.RegionDTO;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class ArticleFulInfo {
    private String id;
    private String title;
    private String description;
    private String content;
    private Integer sharedCount;
    private RegionDTO region;
    private CategoryDTO category;
    private LocalDateTime publishedDate;
    private Integer viewCount;
    private Integer likeCount;
}
