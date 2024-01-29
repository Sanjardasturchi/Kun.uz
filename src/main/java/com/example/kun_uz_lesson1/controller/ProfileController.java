package com.example.kun_uz_lesson1.controller;

import com.example.kun_uz_lesson1.dto.JwtDTO;
import com.example.kun_uz_lesson1.dto.ProfileDTO;
import com.example.kun_uz_lesson1.dto.ProfileFilterDTO;
import com.example.kun_uz_lesson1.enums.ProfileRole;
import com.example.kun_uz_lesson1.repository.ProfileRepository;
import com.example.kun_uz_lesson1.service.ProfileService;
import com.example.kun_uz_lesson1.util.JWTUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/profile")
public class ProfileController {
    @Autowired
    private ProfileService profileService;
    @PostMapping("/adm")
    public ResponseEntity<ProfileDTO> creat(@RequestBody ProfileDTO dto, @RequestHeader("Authorization") String jwt,
                                            HttpServletRequest request){
        Integer id = (Integer) request.getAttribute("id");
        ProfileRole role = (ProfileRole) request.getAttribute("role");

        if (!role.equals(ProfileRole.ADMIN)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        return ResponseEntity.ok(profileService.creat(dto,jwt));
    }
    @PutMapping("/adm/update/{id}")
    public ResponseEntity<ProfileDTO> update(@PathVariable("id") Integer id,@RequestBody ProfileDTO dto, @RequestHeader("Authorization") String jwt){
        return ResponseEntity.ok(profileService.update(id,dto,jwt));
    }
    @PutMapping("/adm/update")
    public ResponseEntity<ProfileDTO> updateOwn(@RequestBody ProfileDTO dto, @RequestHeader("Authorization") String jwt){
        return ResponseEntity.ok(profileService.updateOwn(dto,jwt));
    }
    @GetMapping("/adm/all")
    public ResponseEntity<PageImpl<ProfileDTO>> getAll(@RequestParam(value = "page",defaultValue = "1")Integer page,
                                                       @RequestParam(value = "size",defaultValue = "10")Integer size,
                                                       @RequestHeader("Authorization") String jwt){
        JwtDTO decode = JWTUtil.decode(jwt);
        if (decode.getRole().equals(ProfileRole.ADMIN)) {
//            return ResponseEntity.status(HttpStatus.FORBIDDEN);
        }

        return ResponseEntity.ok(profileService.getAll(page,size,jwt));
    }
    @PutMapping("/adm/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Integer id,@RequestHeader("Authorization") String jwt){
        return ResponseEntity.ok(profileService.delete(id,jwt));
    }
    @GetMapping("/filter")
    public ResponseEntity<List<ProfileDTO>> filter(@RequestBody ProfileFilterDTO filterDTO,@RequestHeader("Authorization")String jwt){
        return ResponseEntity.ok(profileService.filter(filterDTO,jwt));
    }
}
