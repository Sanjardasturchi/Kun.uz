package com.example.kun_uz_lesson1.controller;

import com.example.kun_uz_lesson1.dto.SMSHistoryDTO;
import com.example.kun_uz_lesson1.service.SMSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

//12. SmsHistory
//    1. Create Sms History when sms is send using application. (No need create api)
//    2. Get sms history by phone
//         (id, phone,message,status,type(if necessary),created_date)
//    3. Get sms history be given date
//         (id, phone,message,status,type(if necessary),created_date)
//    4.Pagination (ADMIN)
//         (id, phone,message,status,type(if necessary),created_date)
//
//    (!While sending should some restrictions:
//         For 1 phone 4 sms allowed during 1 minute.)
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
