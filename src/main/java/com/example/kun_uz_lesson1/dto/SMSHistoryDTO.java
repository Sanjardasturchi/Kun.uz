package com.example.kun_uz_lesson1.dto;

import com.example.kun_uz_lesson1.enums.SMSStatus;
import com.example.kun_uz_lesson1.enums.SMSType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

//id, phone,message,status,type(if necessary),created_date,(used_date if necessary)
@Setter
@Getter
public class SMSHistoryDTO {
    private Integer id;
    private String message;
    private SMSStatus status;
    private SMSType type;
    private LocalDateTime createdDate;
    private LocalDateTime usedDate;
}
