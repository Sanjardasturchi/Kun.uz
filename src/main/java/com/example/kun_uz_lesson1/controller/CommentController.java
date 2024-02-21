package com.example.kun_uz_lesson1.controller;

import com.example.kun_uz_lesson1.dto.CommentDTO;
import com.example.kun_uz_lesson1.dto.extra.CommentDeleteDTO;
import com.example.kun_uz_lesson1.dto.extra.CommentFilterDTO;
import com.example.kun_uz_lesson1.dto.extra.CommentUpdateDTO;
import com.example.kun_uz_lesson1.dto.extra.CreatCommentDTO;
import com.example.kun_uz_lesson1.enums.ProfileRole;
import com.example.kun_uz_lesson1.service.CommentService;
import com.example.kun_uz_lesson1.util.HttpRequestUtil;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Comment API list", description = "API list for Comment")
@RestController
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;
    @PostMapping("")
    @PreAuthorize("hasAnyRole('ADMIN','USER','MODERATOR','PUBLISHER')")
    public CommentDTO creat(@RequestBody CreatCommentDTO dto){
        return commentService.creat(dto);
    }
    @PreAuthorize("hasAnyRole('ADMIN','USER','MODERATOR','PUBLISHER')")
    @PutMapping("/update")
    public CommentDTO update(@RequestBody CommentUpdateDTO updateDTO){
        return commentService.update(updateDTO.getContent(),updateDTO.getArticleId(), updateDTO.getCommentId());
    }
    @DeleteMapping("/delete")
    @PreAuthorize("hasAnyRole('ADMIN','USER','MODERATOR','PUBLISHER')")
    public void delete(@RequestBody CommentDeleteDTO deleteDTO){
        commentService.delete(deleteDTO.getArticleId(), deleteDTO.getCommentId());
    }
    @GetMapping("/getCommentsByArticleId/{article_id}")
    public ResponseEntity<List<CommentDTO>>getCommentsByArticleId(@PathVariable("article_id")String articleId){
        return ResponseEntity.ok(commentService.getCommentsByArticleId(articleId));
    }
    @GetMapping("/getCommentsByPagination/{article_id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<PageImpl<CommentDTO>>getCommentsByPagination(@PathVariable("article_id")String articleId,
                                                                       @RequestParam(name = "size",defaultValue = "10")Integer size,
                                                                       @RequestParam(name = "page",defaultValue = "1")Integer page){
        return ResponseEntity.ok(commentService.getCommentsByPagination(size,page,articleId));
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/getCommentsByFilterWithPagination")
    public ResponseEntity<PageImpl<CommentDTO>>getCommentsByFilterWithPagination(@RequestParam(name = "size",defaultValue = "10")Integer size,
                                                                             @RequestParam(name = "page",defaultValue = "1")Integer page,
                                                                             @RequestBody CommentFilterDTO filterDTO){
        return ResponseEntity.ok(commentService.getCommentsByFilterWithPagination(size,page,filterDTO));
    }
    @GetMapping("/getRepliedCommentListByCommentId/{comment_id}")
    public ResponseEntity<List<CommentDTO>>getRepliedCommentListByCommentId(@PathVariable("comment_id")Integer commentId){
        return ResponseEntity.ok(commentService.getRepliedCommentListByCommentId(commentId));
    }



}
