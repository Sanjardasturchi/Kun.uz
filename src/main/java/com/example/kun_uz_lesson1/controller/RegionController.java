package com.example.kun_uz_lesson1.controller;

import com.example.kun_uz_lesson1.config.CustomUserDetails;
import com.example.kun_uz_lesson1.dto.RegionDTO;
import com.example.kun_uz_lesson1.enums.AppLanguage;
import com.example.kun_uz_lesson1.enums.ProfileRole;
import com.example.kun_uz_lesson1.service.RegionService;
import com.example.kun_uz_lesson1.util.HttpRequestUtil;
import com.example.kun_uz_lesson1.util.SpringSecurityUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
//@Valid
@RestController
@RequestMapping("/region")
public class RegionController {
    @Autowired
    private RegionService regionService;
    @PostMapping("/adm")
    public ResponseEntity<RegionDTO> creat(@Valid @RequestBody RegionDTO regionDTO) {
        return ResponseEntity.ok(regionService.create(regionDTO));
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/adm/updateById/{id}")
    public ResponseEntity<RegionDTO> updateById(@PathVariable("id") Integer id,
                                                @RequestBody RegionDTO regionDTO) {
        return ResponseEntity.ok(regionService.update(id, regionDTO));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/adm/deleteById/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(regionService.delete(id));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/adm/all")
    public ResponseEntity<List<RegionDTO>> all() {
        return ResponseEntity.ok(regionService.all());
    }

    @GetMapping("/getByLang")
    public ResponseEntity<List<RegionDTO>> getByLang(@RequestParam(value = "language", defaultValue = "uz") AppLanguage language) {
        return ResponseEntity.ok(regionService.getByLang(language));
    }
    @GetMapping("/change")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> change() {
        return ResponseEntity.ok("DONE");
    }
    @GetMapping("/change2")
    @PreAuthorize("hasAnyRole('ADMIN','MODERATOR')")
    public ResponseEntity<String> change2() {
        return ResponseEntity.ok("DONE");
    }
}
