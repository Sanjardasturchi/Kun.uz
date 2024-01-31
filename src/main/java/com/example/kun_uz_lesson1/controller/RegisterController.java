package com.example.kun_uz_lesson1.controller;

import com.example.kun_uz_lesson1.dto.RegistrationDTO;
import com.example.kun_uz_lesson1.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        return ResponseEntity.ok(registrationService.emailVerification(id));
    }

}
