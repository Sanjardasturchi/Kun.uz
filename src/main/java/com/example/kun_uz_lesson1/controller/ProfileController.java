package com.example.kun_uz_lesson1.controller;

import com.example.kun_uz_lesson1.dto.ProfileDTO;
import com.example.kun_uz_lesson1.dto.ProfileFilterDTO;
import com.example.kun_uz_lesson1.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/profile")
public class ProfileController {
    @Autowired
    private ProfileService profileService;
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/adm")
    public ResponseEntity<ProfileDTO> creat(@RequestBody ProfileDTO dto){
        return ResponseEntity.ok(profileService.creat(dto));
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/adm/update/{id}")
    public ResponseEntity<ProfileDTO> update(@PathVariable("id") Integer id,@RequestBody ProfileDTO dto){
        return ResponseEntity.ok(profileService.update(id,dto));
    }
    @PutMapping("/adm/update")
    public ResponseEntity<ProfileDTO> updateOwn(@RequestBody ProfileDTO dto){
        return ResponseEntity.ok(profileService.updateOwn(dto));
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/adm/all")
    public ResponseEntity<PageImpl<ProfileDTO>> getAll(@RequestParam(value = "page",defaultValue = "1")Integer page,
                                                       @RequestParam(value = "size",defaultValue = "10")Integer size){
        return ResponseEntity.ok(profileService.getAll(page,size));
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/adm/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Integer id){
        return ResponseEntity.ok(profileService.delete(id));
    }
    @GetMapping("/filter")
    public ResponseEntity<List<ProfileDTO>> filter(@RequestBody ProfileFilterDTO filterDTO,@RequestHeader("Authorization")String jwt){
        return ResponseEntity.ok(profileService.filter(filterDTO));
    }
}
