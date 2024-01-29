package com.example.kun_uz_lesson1.controller;

import com.example.kun_uz_lesson1.dto.AuthDTO;
import com.example.kun_uz_lesson1.dto.ProfileDTO;
import com.example.kun_uz_lesson1.dto.RegistrationDTO;
import com.example.kun_uz_lesson1.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
// 1.  Authorization
//       1. Registration (only USER)
//            (name,surname,login/email/phone,password) + validation. password with MD5 encode.
//       2. Login
//            (email/phone,password)
//       3. Verification
//            (phone -> code) or (mail -> link)
//            (should by limit for verification time)
@RestController
@RequestMapping("/auth")
public class AuthController
{
    @Autowired
    private AuthService authService;



    @PostMapping("/login")
    public ResponseEntity<ProfileDTO> login(@RequestBody AuthDTO authDTO){
        return ResponseEntity.ok(authService.auth(authDTO));
    }


}
