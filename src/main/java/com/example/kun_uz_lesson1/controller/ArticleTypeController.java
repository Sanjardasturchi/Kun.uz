package com.example.kun_uz_lesson1.controller;

import com.example.kun_uz_lesson1.dto.ArticleTypeDTO;
import com.example.kun_uz_lesson1.enums.AppLanguage;
import com.example.kun_uz_lesson1.service.ArticleTypeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Tag(name = "ArticleType API list", description = "API list for ArticleType")
@RestController
@RequestMapping("/article_type")
public class ArticleTypeController {
    @Autowired
    private ArticleTypeService articleTypeService;
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("")
    public ResponseEntity<ArticleTypeDTO> creat(@RequestBody ArticleTypeDTO articleTypeDTO) {
        return ResponseEntity.ok(articleTypeService.create(articleTypeDTO));
    }

    @PutMapping("/updateById/{id}")
    public ResponseEntity<ArticleTypeDTO> updateById(@PathVariable("id") Integer id,
                                                     @RequestBody ArticleTypeDTO articleTypeDTO) {
        return ResponseEntity.ok(articleTypeService.updateById(id, articleTypeDTO));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(articleTypeService.delete(id));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/allByPagination")
    public ResponseEntity<PageImpl<ArticleTypeDTO>> allByPagination(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                                                    @RequestParam(value = "size", defaultValue = "10") Integer size) {
        return ResponseEntity.ok(articleTypeService.allByPagination(page, size));
    }
    @GetMapping("/getByLang")
    public ResponseEntity<List<ArticleTypeDTO>> getByLang(@RequestParam(value = "language",defaultValue = "uz") AppLanguage language) {
        return ResponseEntity.ok(articleTypeService.getByLang(language));
    }
}
