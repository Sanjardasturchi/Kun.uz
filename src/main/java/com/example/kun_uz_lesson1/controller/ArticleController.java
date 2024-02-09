package com.example.kun_uz_lesson1.controller;
//6. Article
//    1. CREATE (Moderator) status(NotPublished)
//        (title,description,content,image_id, region_id,category_id, articleType(array))
//    2. Update (Moderator (status to not publish)) (remove old image)
//        (title,description,content,shared_count,image_id, region_id,category_id)
//    3. Delete Article (MODERATOR)
//    4. Change status by id (PUBLISHER) (publish,not_publish)
//    5. Get Last 5 Article By Types  ordered_by_created_date
//        (Berilgan types bo'yicha oxirgi 5ta pubished bo'lgan article ni return qiladi.)
//        ArticleShortInfo
//    6. Get Last 3 Article By Types  ordered_by_created_date
//        (Berilgan types bo'yicha oxirgi 3ta pubished bo'lgan article ni return qiladi.)
//         ArticleShortInfo
//    7. Get Last 8  Articles witch id not included in given list.
//        ([1,2,3])
//        ArticleShortInfo
//    8. Get Article By Id And Lang
//        ArticleFullInfo
//    9. Get Last 4 Article By Types and except given article id.
//        ArticleShortInfo
//    10. Get 4 most read articles
//        ArticleShortInfo
//    11. Get Last 4 Article By TagName (Article ni eng ohirida chiqib turadi.)
//        ArticleShortInfo
//    12. Get Last 5 Article By Types  And By Region Key
//        ArticleShortInfo
//    13. Get Article list by Region Key (Pagination)
//        ArticleShortInfo
//    14. Get Last 5 Article Category Key
//        ArticleShortInfo
//    15. Get Article By Category Key (Pagination)
//        ArticleShortInfo
//    16. Increase Article View Count by Article Id
//        (article_id)
//    17. Increase Share View Count by Article Id
//        (article_id)
//    18. Filter Article (id,title,region_id,category_id,crated_date_from,created_date_to
//        published_date_from,published_date_to,moderator_id,publisher_id,status) with Pagination (PUBLISHER)
//        ArticleShortInfo

//     ArticleShortInfo
//        id(uuid),title,description,image(id,url),published_date
//        ArticleFullInfo
//        id(uuid),title,description,content,shared_count,
//        region(key,name),category(key,name),published_date,view_count,like_count,
//        tagList(name)

import com.example.kun_uz_lesson1.dto.ArticleCreateDTO;
import com.example.kun_uz_lesson1.dto.ArticleDTO;
import com.example.kun_uz_lesson1.dto.ArticleIdListDTO;
import com.example.kun_uz_lesson1.dto.ArticleShortInfo;
import com.example.kun_uz_lesson1.enums.ArticleStatus;
import com.example.kun_uz_lesson1.enums.ProfileRole;
import com.example.kun_uz_lesson1.service.ArticleService;
import com.example.kun_uz_lesson1.util.HttpRequestUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//(title,description,content,image_id, region_id,category_id, articleType(array))
@RestController
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;
    @PostMapping("/mod")
    public void creat(@RequestBody ArticleCreateDTO articleCreateDTO,
                                            HttpServletRequest request) {
        Integer id = HttpRequestUtil.getProfileId(request, ProfileRole.MODERATOR);
        articleService.create(articleCreateDTO,id);
    }

//    @PutMapping("/mod/updateById/{id}")
//    public ResponseEntity<ArticleDTO> updateById(@PathVariable("id") String id,
//                                                     @RequestBody ArticleDTO articleTypeDTO,
//                                                 HttpServletRequest request) {
//        HttpRequestUtil.getProfileId(request, ProfileRole.MODERATOR);
//        return ResponseEntity.ok(articleService.updateById(id, articleTypeDTO));
//    }
    @PutMapping("/mod/update/{id}")
    public ResponseEntity<ArticleDTO> update(@PathVariable("id") String id,
                                                 @RequestBody ArticleCreateDTO articleCreateDTO,
                                                 HttpServletRequest request) {
        Integer profileId = HttpRequestUtil.getProfileId(request, ProfileRole.MODERATOR);
        return ResponseEntity.ok(articleService.update(id, articleCreateDTO,profileId));
    }

    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") String id,
                                         HttpServletRequest request) {
        HttpRequestUtil.getProfileId(request, ProfileRole.MODERATOR);
        return ResponseEntity.ok(articleService.delete(id));
    }
    @PutMapping("/pub/changeStatusById/{id}")
    public ResponseEntity<ArticleDTO> changeStatusById(@PathVariable("id") String id,
                                                       @RequestParam("status")ArticleStatus status,
                                                       HttpServletRequest request) {
        HttpRequestUtil.getProfileId(request, ProfileRole.PUBLISHER);
        return ResponseEntity.ok(articleService.changeStatusById(id, status));
    }
    @GetMapping("/getLastFive")
    public ResponseEntity<List<ArticleShortInfo>> getLastFive(@RequestParam("article_type")Integer id) {
        return ResponseEntity.ok(articleService.getLastFive(id));
    }
    @GetMapping("/getLastThree")
    public ResponseEntity<List<ArticleShortInfo>> getLastThree(@RequestParam("article_type")Integer id) {
        return ResponseEntity.ok(articleService.getLastThree(id));
    }
    @GetMapping("/getLastEightIdNotIncludedInGivenList")
    public ResponseEntity<List<ArticleShortInfo>> getLastEightIdNotIncludedInGivenList(@RequestBody ArticleIdListDTO idList) {
        return ResponseEntity.ok(articleService.getLastEightIdNotIncludedInGivenList(idList));
    }
    @GetMapping("/getLastFourByTypesAndExceptGivenArticleId")
    public ResponseEntity<List<ArticleShortInfo>> getLastFourByTypesAndExceptGivenArticleId(@RequestParam("article_type")Integer id,
                                                                                      @RequestParam("article_id")String article_Id) {
        return ResponseEntity.ok(articleService.getLastFourByTypesAndExceptGivenArticleId(id,article_Id));
    }

    @PutMapping("/increaseArticleViewCount/{id}")
    public ResponseEntity<String> increaseArticleViewCount(@PathVariable("id") String id) {
        return ResponseEntity.ok(articleService.increaseArticleViewCount(id));
    }
    @PutMapping("/increaseArticleSharedCount/{id}")
    public ResponseEntity<String> increaseArticleSharedCount(@PathVariable("id") String id) {
        return ResponseEntity.ok(articleService.increaseArticleSharedCount(id));
    }
    @PutMapping("/getFourMostReadArticles")
    public ResponseEntity<List<ArticleShortInfo>> getFourMostReadArticles(){
        return ResponseEntity.ok(articleService.getFourMostReadArticles());
    }
    @PutMapping("/getFourArticlesByTagName")
    public ResponseEntity<List<ArticleShortInfo>> getFourArticlesByTagName(@PathVariable("tag_name") String tagName){
        return ResponseEntity.ok(articleService.getFourArticlesByTagName(tagName));
    }
//    @PutMapping("/getFiveArticlesByTypeAndRegionKey")
//    public ResponseEntity<List<ArticleShortInfo>> getFiveArticlesByTypeAndRegionKey(@PathVariable("type_id") String typeId,
//                                                                                    @PathVariable("region_id") Integer regionId){
//        return ResponseEntity.ok(articleService.getFiveArticlesByTypeAndRegionKey(typeId,regionId));
//    }

//    @GetMapping("/allByPagination")
//    public ResponseEntity<PageImpl<ArticleDTO>> allByPagination(@RequestParam(value = "page", defaultValue = "1") Integer page,
//                                                                    @RequestParam(value = "size", defaultValue = "10") Integer size,
//                                                                    @RequestHeader("Authorization")String jwt) {
//        return ResponseEntity.ok(articleService.allByPagination(page, size,jwt));
//    }
//    @GetMapping("/getByLang")
//    public ResponseEntity<List<ArticleDTO>> getByLang(@RequestParam(value = "language",defaultValue = "uz") AppLanguage language) {
//        return ResponseEntity.ok(articleService.getByLang(language));
//    }
}
