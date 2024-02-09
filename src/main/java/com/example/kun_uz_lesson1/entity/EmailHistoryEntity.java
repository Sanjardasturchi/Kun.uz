
package com.example.kun_uz_lesson1.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "email_history")
public class EmailHistoryEntity extends BaseEntity{
    @Column(name = "message",columnDefinition = "text")
    private String message;
    private String email;
    private LocalDateTime createdDate;
}
