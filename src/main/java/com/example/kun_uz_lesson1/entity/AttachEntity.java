package com.example.kun_uz_lesson1.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Table(name = "attach")
public class AttachEntity {
//    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Id
    private String id;
    @Column(name = "original_name")
    private String originalName;
    @Column(name = "path")
    private String path;
    @Column(name = "size")
    private Long size;
    @Column(name = "extension")
    private String extension;
    @Column(name = "created_date")
    private LocalDateTime createdData;

}
