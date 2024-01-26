package com.example.kun_uz_lesson1.dto;

import com.example.kun_uz_lesson1.enums.ProfileRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class JwtDTO {
    public Integer id;
    private ProfileRole role;
}
