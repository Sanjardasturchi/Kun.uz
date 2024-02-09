package com.example.kun_uz_lesson1.dto;

import com.example.kun_uz_lesson1.enums.SMSStatus;
import com.example.kun_uz_lesson1.enums.SMSType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
//id, message, email, created_data
@Setter
@Getter
public class EmailHistoryDTO {
    private Integer id;
    private String message;
    private String email;
    private LocalDateTime createdDate;
}
