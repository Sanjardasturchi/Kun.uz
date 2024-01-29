package com.example.kun_uz_lesson1.controller;

import com.example.kun_uz_lesson1.dto.ArticleTypeDTO;
import com.example.kun_uz_lesson1.enums.AppLanguage;
import com.example.kun_uz_lesson1.service.ArticleTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/article_type")
public class ArticleTypeController {
    @Autowired
    private ArticleTypeService articleTypeService;
    @PostMapping("")
    public ResponseEntity<ArticleTypeDTO> creat(@RequestBody ArticleTypeDTO articleTypeDTO,
                                                @RequestHeader("Authorization")String jwt) {
        return ResponseEntity.ok(articleTypeService.create(articleTypeDTO,jwt));
    }

    @PutMapping("/updateById/{id}")
    public ResponseEntity<ArticleTypeDTO> updateById(@PathVariable("id") Integer id,
                                                     @RequestBody ArticleTypeDTO articleTypeDTO,
                                                     @RequestHeader("Authorization")String jwt) {
        return ResponseEntity.ok(articleTypeService.updateById(id, articleTypeDTO,jwt));
    }

    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Integer id,@RequestHeader("Authorization")String jwt) {
        return ResponseEntity.ok(articleTypeService.delete(id,jwt));
    }

    @GetMapping("/allByPagination")
    public ResponseEntity<PageImpl<ArticleTypeDTO>> allByPagination(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                                                    @RequestParam(value = "size", defaultValue = "10") Integer size,
                                                                    @RequestHeader("Authorization")String jwt) {
        return ResponseEntity.ok(articleTypeService.allByPagination(page, size,jwt));
    }
    @GetMapping("/getByLang")
    public ResponseEntity<List<ArticleTypeDTO>> getByLang(@RequestParam(value = "language",defaultValue = "uz") AppLanguage language) {
        return ResponseEntity.ok(articleTypeService.getByLang(language));
    }
}
