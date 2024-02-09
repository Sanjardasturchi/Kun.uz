package com.example.kun_uz_lesson1.controller;

import com.example.kun_uz_lesson1.dto.EmailHistoryDTO;
import com.example.kun_uz_lesson1.enums.ProfileRole;
import com.example.kun_uz_lesson1.service.EmailHistoryService;
import com.example.kun_uz_lesson1.util.HttpRequestUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

//13. EmailHistory
//    id, message, email, created_data
//    1. Create EmailHistory when email is send using application. (No need create api)
//    2. Get EmailHistory by email
//            (id, email,message,created_date)
//    3. Get EmailHistory  by given date
//            (id, email,message,created_date)
//    4. Pagination (ADMIN)
//            (id, email,message,created_date)
//
//       (!Should be limit for email sending. For 1 email 4 sms allowed during 1 minut. )
@RestController
@RequestMapping("/email")
public class EmailController {
    @Autowired
    private EmailHistoryService emailHistoryService;

    @GetMapping("/getByEmail/{email}")
    public ResponseEntity<List<EmailHistoryDTO>> getByEmail(@PathVariable("email")String email){
        return ResponseEntity.ok(emailHistoryService.getByEmail(email));
    }
    @GetMapping("/getByGivenDate/{date}")
    public ResponseEntity<List<EmailHistoryDTO>> getByGivenDate(@PathVariable("date") LocalDate date){
        return ResponseEntity.ok(emailHistoryService.getByGivenDate(date));
    }
    @GetMapping("/adm/getByPagination")
    public ResponseEntity<PageImpl<EmailHistoryDTO>> getByPagination(@RequestParam(value = "page",defaultValue = "1") Integer page,
                                                                     @RequestParam(value = "size",defaultValue = "10") Integer size,
                                                                     HttpServletRequest request){
        HttpRequestUtil.getProfileId(request, ProfileRole.ADMIN);
        Pageable pageable = PageRequest.of(page - 1, size, Sort.Direction.DESC, "createdDate");
        return ResponseEntity.ok(emailHistoryService.getByPagination(pageable));
    }

}
