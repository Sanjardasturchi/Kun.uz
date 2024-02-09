package com.example.kun_uz_lesson1.entity;

import com.example.kun_uz_lesson1.enums.SMSStatus;
import com.example.kun_uz_lesson1.enums.SMSType;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

//id, phone,message,status,type(if necessary),created_date,(used_date if necessary)
@Setter
@Getter
@Entity
@Table(name = "sms_history")
public class SMSHistoryEntity extends BaseEntity{
    private String message;
    private SMSStatus status;
    private SMSType type;
    private LocalDateTime createdDate;
    private LocalDateTime usedDate;
}
