package com.example.kun_uz_lesson1.controller;

import com.example.kun_uz_lesson1.dto.AuthDTO;
import com.example.kun_uz_lesson1.dto.ProfileDTO;
import com.example.kun_uz_lesson1.enums.AppLanguage;
import com.example.kun_uz_lesson1.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// 1.  Authorization
//       1. Registration (only USER)
//            (name,surname,login/email/phone,password) + validation. password with MD5 encode.
//       2. Login
//            (email/phone,password)
//       3. Verification
//            (phone -> code) or (mail -> link)
//            (should by limit for verification time)
@Slf4j
@Tag(name = "Authorization Api list", description = "Api list for Authorization")
@RestController
@RequestMapping("/auth")
public class AuthController
{
    @Autowired
    private AuthService authService;

//    private Logger log = LoggerFactory.getLogger(AuthController.class);

    @PostMapping("/login")
    @Operation( summary = "Api for login", description = "this api used for authorization")
    public ResponseEntity<ProfileDTO> login(@RequestBody AuthDTO authDTO,
                                            @RequestHeader(value = "Accept-Language",defaultValue = "uz")
                                            AppLanguage language){
        log.trace("Login in Trace ");
        log.debug("Login in Debug ");
        log.info("Login {} ",authDTO.getEmail());
        log.warn("Login {} ",authDTO.getEmail());
        log.error("Login {} ",authDTO.getEmail());
        return ResponseEntity.ok(authService.auth(authDTO,language));
    }


}
