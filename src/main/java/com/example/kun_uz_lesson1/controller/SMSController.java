package com.example.kun_uz_lesson1.controller;

import com.example.kun_uz_lesson1.dto.SMSHistoryDTO;
import com.example.kun_uz_lesson1.service.SMSService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Tag(name = "SMS API list", description = "API list for SMS")
@RestController
@RequestMapping("/sms")
public class SMSController {
    @Autowired
    private SMSService smsService;

    @GetMapping("/getByPhone/{phone}")
    public ResponseEntity<List<SMSHistoryDTO>> getByPhone(@PathVariable("phone")String phone){
        return ResponseEntity.ok(smsService.getByPhone(phone));
    }
    @GetMapping("/getByGivenDate/{date}")
    public ResponseEntity<List<SMSHistoryDTO>> getByGivenDate(@PathVariable("date") LocalDate date){
        return ResponseEntity.ok(smsService.getByGivenDate(date));
    }
    @GetMapping("/getByPagination")
    public ResponseEntity<List<SMSHistoryDTO>> getByPagination(@RequestParam("page") Integer page,
                                                               @RequestParam("size") Integer size){
        return ResponseEntity.ok(smsService.getByPagination(page,size));
    }


}
