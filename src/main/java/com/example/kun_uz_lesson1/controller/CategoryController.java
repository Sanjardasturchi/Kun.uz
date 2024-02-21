package com.example.kun_uz_lesson1.controller;

import com.example.kun_uz_lesson1.dto.CategoryDTO;
import com.example.kun_uz_lesson1.enums.AppLanguage;
import com.example.kun_uz_lesson1.service.CategoryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Tag(name = "Category API list", description = "API list for Category")
@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("")
    public ResponseEntity<CategoryDTO> creat(@RequestBody CategoryDTO categoryDTO) {
        return ResponseEntity.ok(categoryService.create(categoryDTO));
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/updateById/{id}")
    public ResponseEntity<CategoryDTO> updateById(@PathVariable("id") Integer id,
                                                @RequestBody CategoryDTO categoryDTO) {
        return ResponseEntity.ok(categoryService.update(id,categoryDTO));
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(categoryService.delete(id));
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/all")
    public ResponseEntity<List<CategoryDTO>> all() {
        return ResponseEntity.ok(categoryService.all());
    }
    @GetMapping("/getByLang")
    public ResponseEntity<List<CategoryDTO>> getByLang( @RequestParam(value = "language",defaultValue = "uz") AppLanguage language) {
        return ResponseEntity.ok(categoryService.getByLang(language));
    }
}
