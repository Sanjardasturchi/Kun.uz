package com.example.kun_uz_lesson1.entity;

import com.example.kun_uz_lesson1.enums.ProfileRole;
import com.example.kun_uz_lesson1.enums.ProfileStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

//id,name,surname,email,phone,password,status,role,visible,created_date,photo_id
@Setter
@Getter
@Entity
@Table(name = "profile")
public class ProfileEntity extends BaseEntity{
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String surname;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private ProfileStatus status;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private ProfileRole role;

}
