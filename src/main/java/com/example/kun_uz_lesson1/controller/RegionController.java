package com.example.kun_uz_lesson1.controller;

import com.example.kun_uz_lesson1.dto.RegionDTO;
import com.example.kun_uz_lesson1.enums.AppLanguage;
import com.example.kun_uz_lesson1.enums.ProfileRole;
import com.example.kun_uz_lesson1.service.RegionService;
import com.example.kun_uz_lesson1.util.HttpRequestUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
//@Valid
@RestController
@RequestMapping("/region")
public class RegionController {
    @Autowired
    private RegionService regionService;
    @PostMapping("/adm")
    public ResponseEntity<RegionDTO> creat(@Valid @RequestBody RegionDTO regionDTO,
                                           HttpServletRequest request) {
        Integer id = HttpRequestUtil.getProfileId(request, ProfileRole.ADMIN);
        return ResponseEntity.ok(regionService.create(regionDTO));
    }

    @PutMapping("/adm/updateById/{id}")
    public ResponseEntity<RegionDTO> updateById(@PathVariable("id") Integer id,
                                                @RequestBody RegionDTO regionDTO,
                                                @RequestHeader("Authorization") String jwt) {
        return ResponseEntity.ok(regionService.update(id, regionDTO,jwt));
    }

    @DeleteMapping("/adm/deleteById/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Integer id,@RequestHeader("Authorization")String jwt) {
        return ResponseEntity.ok(regionService.delete(id,jwt));
    }

    @GetMapping("/adm/all")
    public ResponseEntity<List<RegionDTO>> all(@RequestHeader("Authorization")String jwt) {
        return ResponseEntity.ok(regionService.all(jwt));
    }

    @GetMapping("/getByLang")
    public ResponseEntity<List<RegionDTO>> getByLang(@RequestParam(value = "language", defaultValue = "uz") AppLanguage language) {
        return ResponseEntity.ok(regionService.getByLang(language));
    }
}
