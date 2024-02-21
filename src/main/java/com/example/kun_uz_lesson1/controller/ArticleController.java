package com.example.kun_uz_lesson1.controller;

import com.example.kun_uz_lesson1.dto.extra.ArticleCreateDTO;
import com.example.kun_uz_lesson1.dto.ArticleDTO;
import com.example.kun_uz_lesson1.dto.extra.ArticleFilterDTO;
import com.example.kun_uz_lesson1.dto.extra.ArticleIdListDTO;
import com.example.kun_uz_lesson1.dto.ArticleShortInfo;
import com.example.kun_uz_lesson1.enums.AppLanguage;
import com.example.kun_uz_lesson1.enums.ArticleStatus;
import com.example.kun_uz_lesson1.service.ArticleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Tag(name = "Article API list", description = "API list for Article")
@RestController
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @Operation(summary = "API for creat",description = "This API for create a new Article")
    @PreAuthorize("hasRole('MODERATOR')")
    @PostMapping("/mod")
    public void creat(@Valid @RequestBody ArticleCreateDTO articleCreateDTO) {
        articleService.create(articleCreateDTO);
    }

    @Operation(summary = "API for update",description = "This API for update Article fields")
    @PreAuthorize("hasRole('MODERATOR')")
    @PutMapping("/mod/update/{id}")
    public ResponseEntity<ArticleDTO> update(@PathVariable("id") String id,
                                             @Valid @RequestBody ArticleCreateDTO articleCreateDTO) {
        return ResponseEntity.ok(articleService.update(id, articleCreateDTO));
    }

    @Operation(summary = "API for delete",description = "This API for delete Article")
    @PreAuthorize("hasRole('MODERATOR')")
    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") String id) {
        return ResponseEntity.ok(articleService.delete(id));
    }

    @Operation(summary = "API for update",description = "This API for update Article status")
    @PreAuthorize("hasRole('PUBLISHER')")
    @PutMapping("/pub/changeStatusById/{id}")
    public ResponseEntity<ArticleDTO> changeStatusById(@PathVariable("id") String id,
                                                       @RequestParam("status") ArticleStatus status) {
        return ResponseEntity.ok(articleService.changeStatusById(id, status));
    }

    @Operation(summary = "API for get",description = "This API for get last 5 Article")
    @GetMapping("/getLastFive")
    public ResponseEntity<List<ArticleShortInfo>> getLastFive(@RequestParam("article_type") Integer id) {
        return ResponseEntity.ok(articleService.getLastFive(id));
    }

    @Operation(summary = "API for get",description = "This API for get last 3 Article")
    @GetMapping("/getLastThree")
    public ResponseEntity<List<ArticleShortInfo>> getLastThree(@RequestParam("article_type") Integer id) {
        return ResponseEntity.ok(articleService.getLastThree(id));
    }

    @Operation(summary = "API for get",description = "This API for get Last Eight Id Not Included In Given List")
    @GetMapping("/getLastEightIdNotIncludedInGivenList")
    public ResponseEntity<List<ArticleShortInfo>> getLastEightIdNotIncludedInGivenList(@RequestBody ArticleIdListDTO idList) {
        return ResponseEntity.ok(articleService.getLastEightIdNotIncludedInGivenList(idList));
    }

    @Operation(summary = "API for get",description = "This API for get Last Four By Types And Except Given Article Id")
    @GetMapping("/getLastFourByTypesAndExceptGivenArticleId")
    public ResponseEntity<List<ArticleShortInfo>> getLastFourByTypesAndExceptGivenArticleId(@RequestParam("article_type") Integer id,
                                                                                            @RequestParam("article_id") String articleId) {
        return ResponseEntity.ok(articleService.getLastFourByTypesAndExceptGivenArticleId(id, articleId));
    }

    @Operation(summary = "API for increase",description = "This API for increase Article View Count")
    @PutMapping("/increaseArticleViewCount/{id}")
    public ResponseEntity<String> increaseArticleViewCount(@PathVariable("id") String id) {
        return ResponseEntity.ok(articleService.increaseArticleViewCount(id));
    }

    @Operation(summary = "API for increase",description = "This API for increase Article Shared Count")
    @PutMapping("/increaseArticleSharedCount/{id}")
    public ResponseEntity<String> increaseArticleSharedCount(@PathVariable("id") String id) {
        return ResponseEntity.ok(articleService.increaseArticleSharedCount(id));
    }

    @Operation(summary = "API for get",description = "This API for get Four Most Read Articles")
    @GetMapping("/getFourMostReadArticles")
    public ResponseEntity<List<ArticleShortInfo>> getFourMostReadArticles() {
        return ResponseEntity.ok(articleService.getFourMostReadArticles());
    }

    @Operation(summary = "API for get",description = "This API for get Four Articles By Tag Name")
    @GetMapping("/getFourArticlesByTagName")
    public ResponseEntity<List<ArticleShortInfo>> getFourArticlesByTagName(@RequestParam("tag_name") String tagName) {
        return ResponseEntity.ok(articleService.getFourArticlesByTagName(tagName));
    }
    @Operation(summary = "API for get",description = "This API for get Last Five Articles By Type And Region Key")
    @GetMapping("/getLastFiveArticlesByTypeAndRegionKey")
    public ResponseEntity<List<ArticleShortInfo>> getLastFiveArticlesByTypeAndRegionKey(@RequestParam("type_id") Integer typeId,
                                                                                    @RequestParam("region_id") Integer regionId){
        return ResponseEntity.ok(articleService.getFiveArticlesByTypeAndRegionKey(typeId,regionId));
    }

    @Operation(summary = "API for get",description = "This API for get All By Region Key By Pagination")
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/allByRegionKeyByPagination")
    public ResponseEntity<PageImpl<ArticleDTO>> allByRegionKeyByPagination( @RequestParam("region_id") Integer regionId,
                                                                            @RequestParam(value = "page", defaultValue = "1") Integer page,
                                                                            @RequestParam(value = "size", defaultValue = "10") Integer size) {
        return ResponseEntity.ok(articleService.allByRegionKeyByPagination(regionId,page, size));
    }
    @Operation(summary = "API for get",description = "This API for get Last Five Articles By Category Key")
    @GetMapping("/getLastFiveArticlesByCategoryKey")
    public ResponseEntity<List<ArticleShortInfo>> getLastFiveArticlesByCategoryKey(@RequestParam("category_id") Integer categoryId){
        return ResponseEntity.ok(articleService.getLastFiveArticlesByCategoryKey(categoryId));
    }
    @Operation(summary = "API for get",description = "This API for get all By Category Key By Pagination")
    @GetMapping("/allByCategoryKeyByPagination")
    public ResponseEntity<PageImpl<ArticleDTO>> allByCategoryKeyByPagination( @RequestParam("category_id") Integer categoryId,
                                                                            @RequestParam(value = "page", defaultValue = "1") Integer page,
                                                                            @RequestParam(value = "size", defaultValue = "10") Integer size) {
        return ResponseEntity.ok(articleService.allByCategoryKeyByPagination(categoryId,page, size));
    }

    @Operation(summary = "API for get",description = "This API for get all By Filter With Pagination")
    @GetMapping("/allByFilterWithPagination")
    public ResponseEntity<PageImpl<ArticleShortInfo>> allByFilterWithPagination(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                                        @RequestParam(value = "size", defaultValue = "10") Integer size,
                                                        @RequestBody ArticleFilterDTO filterDTO) {
        return ResponseEntity.ok(articleService.allByFilterWithPagination(filterDTO,page,size));
    }

    @Operation(summary = "API for get",description = "This API for get By Lang")
    @GetMapping("/getByLang/{id}")
    public ResponseEntity<ArticleDTO> getByLang(@PathVariable("id") String id,@RequestParam(value = "language",defaultValue = "uz") AppLanguage language) {
        return ResponseEntity.ok(articleService.getByLang(id,language));
    }
}
