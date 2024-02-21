package com.example.kun_uz_lesson1.controller;

import com.example.kun_uz_lesson1.dto.RegistrationDTO;
import com.example.kun_uz_lesson1.service.RegistrationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@Tag(name = "Register API list", description = "API list for Register")
@Slf4j
@RestController
@RequestMapping("/register")
public class RegisterController {
    @Autowired
    private RegistrationService registrationService;
    @PostMapping("")
    public String registration(@RequestBody RegistrationDTO registration){
        return registrationService.register(registration);
    }
    @GetMapping("/verification/email/{id}")
    public ResponseEntity<String> emailVerification(@PathVariable("id") Integer id) {
        log.info("Login {} ",id);
        return ResponseEntity.ok(registrationService.emailVerification(id));
    }

}
