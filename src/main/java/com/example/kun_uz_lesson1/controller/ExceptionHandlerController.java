package com.example.kun_uz_lesson1.controller;

import com.example.kun_uz_lesson1.exp.AppBadException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerController {
    @ExceptionHandler(ArithmeticException.class)
    private ResponseEntity<?> handle(AppBadException e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    private ResponseEntity<?> handle(RuntimeException e) {
        return ResponseEntity.internalServerError().body(e.getMessage());
    }

}
