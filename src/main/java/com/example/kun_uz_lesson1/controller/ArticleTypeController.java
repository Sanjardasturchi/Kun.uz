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
    //3. ArticleType
    //    1. Create  (ADMIN)
    //        (order_number,name_uz, name_ru, name_en)
    //    2. Update by id (ADMIN)
    //        (order_number,name_uz, name_ru, name_en)
    //    3. Delete by id (ADMIN)
    //    4. Get List (ADMIN) (Pagination)
    //        (id,key,name_uz, name_ru, name_en,visible,created_date)
    //    5. Get By Lang (Language keladi shu language dagi name larini berib yuboramiz)
    //        (id,key,name) (name ga tegishli name_.. dagi qiymat qo'yiladi.)
    @PostMapping("")
    public ResponseEntity<ArticleTypeDTO> creat(@RequestBody ArticleTypeDTO articleTypeDTO) {
        return ResponseEntity.ok(articleTypeService.create(articleTypeDTO));
    }

    @PutMapping("/updateById/{id}")
    public ResponseEntity<ArticleTypeDTO> updateById(@PathVariable("id") Integer id,
                                                     @RequestBody ArticleTypeDTO articleTypeDTO) {
        return ResponseEntity.ok(articleTypeService.updateById(id, articleTypeDTO));
    }

    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(articleTypeService.delete(id));
    }

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
