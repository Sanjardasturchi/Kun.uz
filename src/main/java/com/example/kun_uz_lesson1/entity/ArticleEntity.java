package com.example.kun_uz_lesson1.entity;

import com.example.kun_uz_lesson1.dto.ArticleTypeDTO;
import com.example.kun_uz_lesson1.enums.ArticleStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Setter
@Getter
@Entity
@Table(name = "article")
public class ArticleEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @GenericGenerator(name = "UUID",strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    @Column(name = "title")
    private String title;
    @Column(name = "title_uz")
    private String titleUz;
    @Column(name = "title_ru")
    private String titleRu;
    @Column(name = "title_en")
    private String titleEn;
    @Column(name = "description",columnDefinition = "TEXT")
    private String description;
    @Column(name = "description_uz",columnDefinition = "TEXT")
    private String descriptionUz;
    @Column(name = "description_ru",columnDefinition = "TEXT")
    private String descriptionRu;
    @Column(name = "description_en",columnDefinition = "TEXT")
    private String descriptionEn;
    @Column(name = "content",columnDefinition = "TEXT")
    private String content;
    @Column(name = "content_uz",columnDefinition = "TEXT")
    private String contentUz;
    @Column(name = "content_ru",columnDefinition = "TEXT")
    private String contentRu;
    @Column(name = "content_en",columnDefinition = "TEXT")
    private String contentEn;
    @Column(name = "shared_count")
    private Integer sharedCount=0;
    @Column(name = "photo_id")
    private String photoId;
    @Column(name = "created_date")
    private LocalDateTime createdDate;
    @Column(name = "published_date")
    private LocalDateTime publishedDate;
    private Boolean visible;
    @Column(name = "view_count")
    private Integer viewCount=0;
    @Column(name = "status")
    private ArticleStatus status;

    @OneToMany(mappedBy = "article")
    private List<ArticleTypesEntity> articleTypesList;

    @Column(name = "region_id")
    private Integer regionId;
    @Column(name = "category_id")
    private Integer categoryId;
    @Column(name = "moderator_id")
    private Integer moderatorId;
    @Column(name = "publisher_id")
    private Integer publisherId;
    @Column(name = "like_count")
    private Integer likeCount;
    @Column(name = "dislike_count")
    private Integer dislikeCount;



    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "photo_id",insertable = false,updatable = false)
    private AttachEntity photo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id",insertable = false,updatable = false)
    private RegionEntity region;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id",insertable = false,updatable = false)
    private CategoryEntity category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "moderator_id",nullable = false,insertable = false,updatable = false)//
    private ProfileEntity moderator;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "publisher_id",insertable = false,updatable = false)
    private ProfileEntity publisher;
}