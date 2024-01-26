package com.example.kun_uz_lesson1.controller;

import com.example.kun_uz_lesson1.dto.ProfileDTO;
import com.example.kun_uz_lesson1.dto.ProfileFilterDTO;
import com.example.kun_uz_lesson1.repository.ProfileRepository;
import com.example.kun_uz_lesson1.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/profile")
public class ProfileController {
    //1. Create profile (ADMIN) (can create MODERATOR,PUBLISHER))
    //         (name,surname,email,phone,password,status,role)
    //    2. Update Profile (ADMIN)
    //    3. Update Profile Detail (ANY) (Profile updates own details)
    //    4. Profile List (ADMIN) (Pagination)
    //    5. Delete Profile By Id (ADMIN)
    //    6. Update Photo (ANY) (Murojat qilgan odamni rasmini upda qilish)   (remove old image)
    //           photo_id  TODO
    //    7. Filter (name,surname,phone,role,created_date_from,created_date_to)
    @Autowired
    private ProfileService profileService;
    @PostMapping("")
    public ResponseEntity<ProfileDTO> creat(@RequestBody ProfileDTO dto, @RequestHeader("Authorization") String jwt){
       return ResponseEntity.ok(profileService.creat(dto,jwt));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<ProfileDTO> update(@PathVariable("id") Integer id,@RequestBody ProfileDTO dto, @RequestHeader("Authorization") String jwt){
        return ResponseEntity.ok(profileService.update(id,dto,jwt));
    }
    @PutMapping("/update")
    public ResponseEntity<ProfileDTO> updateOwn(@RequestBody ProfileDTO dto, @RequestHeader("Authorization") String jwt){
        return ResponseEntity.ok(profileService.updateOwn(dto,jwt));
    }
    @GetMapping("/all")
    public ResponseEntity<PageImpl<ProfileDTO>> getAll(@RequestParam(value = "page",defaultValue = "1")Integer page,
                                                       @RequestParam(value = "size",defaultValue = "10")Integer size,
                                                       @RequestHeader("Authorization") String jwt){
        return ResponseEntity.ok(profileService.getAll(page,size,jwt));
    }
    @PutMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Integer id,@RequestHeader("Authorization") String jwt){
        return ResponseEntity.ok(profileService.delete(id,jwt));
    }
    @GetMapping("/filter")
    public ResponseEntity<List<ProfileDTO>> filter(@RequestBody ProfileFilterDTO filterDTO,@RequestHeader("Authorization")String jwt){
        return ResponseEntity.ok(profileService.filter(filterDTO,jwt));
    }
}
