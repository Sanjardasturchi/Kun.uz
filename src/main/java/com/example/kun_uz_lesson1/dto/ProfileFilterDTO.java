package com.example.kun_uz_lesson1.dto;

import com.example.kun_uz_lesson1.enums.ProfileRole;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

//name,surname,phone,role,created_date_from,created_date_to
@Setter
@Getter
public class ProfileFilterDTO {
    private String name;
    private String surname;
    private String password;
    private ProfileRole role;
    private LocalDate fromDate;
    private LocalDate toDate;
}
