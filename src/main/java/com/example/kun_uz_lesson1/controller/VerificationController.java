package com.example.kun_uz_lesson1.controller;

import com.example.kun_uz_lesson1.dto.ProfileDTO;
import com.example.kun_uz_lesson1.dto.VerificationDTO;
import com.example.kun_uz_lesson1.service.VerificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/verification")
public class VerificationController {
    @Autowired
    private VerificationService verificationService;
    @PostMapping("")
    public ResponseEntity<ProfileDTO> verification(@RequestBody VerificationDTO verificationDTO) {
        return ResponseEntity.ok(verificationService.verification(verificationDTO));
    }
    @PostMapping("/verification/email/{id}")
    public ResponseEntity<String> emailVerification(@PathVariable("jwt")String jwt) {
        return ResponseEntity.ok(verificationService.emailVerification(jwt));
    }
}