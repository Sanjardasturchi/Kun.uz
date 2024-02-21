package com.example.kun_uz_lesson1.controller;

import com.example.kun_uz_lesson1.service.InitService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@Tag(name = "Init API list", description = "API list for Init")
@RestController
@RequestMapping("/init")
public class InitController {
    @Autowired
    private InitService initService;

    @GetMapping("/admin")
    public String initAdmin(){
        initService.initAdmin();
        return "DONE";
    }
}
