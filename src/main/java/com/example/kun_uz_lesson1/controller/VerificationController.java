package com.example.kun_uz_lesson1.controller;

import com.example.kun_uz_lesson1.dto.ProfileDTO;
import com.example.kun_uz_lesson1.dto.VerificationDTO;
import com.example.kun_uz_lesson1.service.VerificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@Slf4j
@RestController
@RequestMapping("/verification")
public class VerificationController {
    @Autowired
    private VerificationService verificationService;
    @PostMapping("")
    public ResponseEntity<ProfileDTO> verification(@RequestBody VerificationDTO verificationDTO) {
        return ResponseEntity.ok(verificationService.verification(verificationDTO));
    }
    @GetMapping("/email/{jwt}")
    public ResponseEntity<String> emailVerification(@PathVariable("jwt")String jwt) {
        log.info("Login {} ",jwt);
        return ResponseEntity.ok(verificationService.emailVerification(jwt));
    }
}