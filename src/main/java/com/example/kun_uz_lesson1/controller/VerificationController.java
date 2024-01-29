package com.example.kun_uz_lesson1.controller;

import com.example.kun_uz_lesson1.dto.AuthDTO;
import com.example.kun_uz_lesson1.dto.ProfileDTO;
import com.example.kun_uz_lesson1.dto.VerificationDTO;
import com.example.kun_uz_lesson1.service.RegistrationService;
import com.example.kun_uz_lesson1.service.VerificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/verification")
public class VerificationController {
    @Autowired
    private VerificationService verificationService;
    @PostMapping("")
    public ResponseEntity<ProfileDTO> verification(@RequestBody VerificationDTO verificationDTO) {
        return ResponseEntity.ok(verificationService.verification(verificationDTO));
    }
}
