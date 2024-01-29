package com.example.kun_uz_lesson1.controller;

import com.example.kun_uz_lesson1.dto.CategoryDTO;
import com.example.kun_uz_lesson1.enums.AppLanguage;
import com.example.kun_uz_lesson1.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @PostMapping("")
    public ResponseEntity<CategoryDTO> creat(@RequestBody CategoryDTO categoryDTO,@RequestHeader("Authorization")String jwt) {
        return ResponseEntity.ok(categoryService.create(categoryDTO,jwt));
    }
    @PutMapping("/updateById/{id}")
    public ResponseEntity<CategoryDTO> updateById(@PathVariable("id") Integer id,
                                                @RequestBody CategoryDTO categoryDTO,@RequestHeader("Authorization")String jwt) {
        return ResponseEntity.ok(categoryService.update(id,categoryDTO,jwt));
    }
    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Integer id,@RequestHeader("Authorization")String jwt) {
        return ResponseEntity.ok(categoryService.delete(id,jwt));
    }
    @GetMapping("/all")
    public ResponseEntity<List<CategoryDTO>> all(@RequestHeader("Authorization")String jwt) {
        return ResponseEntity.ok(categoryService.all(jwt));
    }
    @GetMapping("/getByLang")
    public ResponseEntity<List<CategoryDTO>> getByLang( @RequestParam(value = "language",defaultValue = "uz") AppLanguage language) {
        return ResponseEntity.ok(categoryService.getByLang(language));
    }
}
