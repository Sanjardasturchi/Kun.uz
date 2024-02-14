package com.example.kun_uz_lesson1.service;

import com.example.kun_uz_lesson1.config.CustomUserDetails;
import com.example.kun_uz_lesson1.dto.ArticleDTO;
import com.example.kun_uz_lesson1.dto.CommentDTO;
import com.example.kun_uz_lesson1.dto.ProfileDTO;
import com.example.kun_uz_lesson1.dto.extra.CommentFilterDTO;
import com.example.kun_uz_lesson1.dto.extra.CreatCommentDTO;
import com.example.kun_uz_lesson1.entity.ArticleEntity;
import com.example.kun_uz_lesson1.entity.CommentEntity;
import com.example.kun_uz_lesson1.entity.ProfileEntity;
import com.example.kun_uz_lesson1.enums.ProfileRole;
import com.example.kun_uz_lesson1.exp.AppBadException;
import com.example.kun_uz_lesson1.repository.CommentCustomRepository;
import com.example.kun_uz_lesson1.repository.CommentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private CommentCustomRepository commentCustomRepository;
    @Autowired
    private ProfileService profileService;

    public CommentDTO creat(CreatCommentDTO dto) {
        CustomUserDetails userDetails =(CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Integer profileId = userDetails.getId();

        CommentEntity entity = new CommentEntity(dto.getContent(), dto.getArticleId());
        if (dto.getReplyId() != null) {
            entity.setReplyId(dto.getReplyId());
        }
        entity.setProfileId(profileId);
        commentRepository.save(entity);
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setId(entity.getId());
        commentDTO.setProfileId(entity.getProfileId());
        commentDTO.setCreatedDate(entity.getCreatedDate());
        commentDTO.setContent(entity.getContent());
        commentDTO.setReplyId(entity.getReplyId());
        commentDTO.setArticleId(entity.getArticleId());
        return commentDTO;
    }

    public CommentDTO update(String content, String articleId, Integer commentId) {
        CustomUserDetails userDetails =(CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Integer profileId = userDetails.getId();

        Optional<CommentEntity> optional = commentRepository.findByIdAndArticleId(commentId, articleId);
        if (optional.isEmpty()) {
            log.warn("Comment not found CommentService method :: update");
            throw new AppBadException("Comment not found");
        }
        CommentEntity comment = optional.get();
        ProfileEntity profile = profileService.get(profileId);
        if (!(profile.getRole().equals(ProfileRole.ROLE_ADMIN) && comment.getProfileId().equals(profileId))) {
            log.warn("You can not update this comment! CommentService method :: update");
            throw new AppBadException("You can not update this comment!");
        }
        comment.setContent(content);
        comment.setUpdatedDate(LocalDateTime.now());
        commentRepository.save(comment);

        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setId(comment.getId());
        commentDTO.setProfileId(comment.getProfileId());
        commentDTO.setCreatedDate(comment.getCreatedDate());
        commentDTO.setContent(comment.getContent());
        commentDTO.setReplyId(comment.getReplyId());
        commentDTO.setArticleId(comment.getArticleId());
        commentDTO.setUpdateDate(comment.getUpdatedDate());
        return commentDTO;
    }

    public void delete(String articleId, Integer commentId) {
        CustomUserDetails userDetails =(CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Integer profileId = userDetails.getId();

        Optional<CommentEntity> optional = commentRepository.findByIdAndArticleId(commentId, articleId);
        if (optional.isEmpty()) {
            log.warn("Comment not found CommentService method :: delete");
            throw new AppBadException("Comment not found");
        }
        CommentEntity comment = optional.get();
        ProfileEntity profile = profileService.get(profileId);
        if (!(profile.getRole().equals(ProfileRole.ROLE_ADMIN) && comment.getProfileId().equals(profileId))) {
            log.warn("You can not update this comment! CommentService method :: delete");
            throw new AppBadException("You can not update this comment!");
        }
        commentRepository.deleteByArticleId(articleId,commentId);
    }

    public List<CommentDTO> getCommentsByArticleId(String articleId) {
        List<CommentEntity> list = commentRepository.findByArticleId(articleId);
        if (list == null) {
            log.warn("Comments not found CommentService method :: delete");
            throw new AppBadException("Comments not found");
        }
        List<CommentDTO> dtoList = new LinkedList<>();
        for (CommentEntity entity : list) {
            ProfileEntity profile = entity.getProfile();
            CommentDTO commentDTO = toDTO(entity);
            ProfileDTO profileDTO = new ProfileDTO();
            profileDTO.setId(profile.getId());
            profileDTO.setName(profile.getName());
            profileDTO.setSurname(profile.getSurname());
            commentDTO.setProfile(profileDTO);
            dtoList.add(commentDTO);
        }
        return dtoList;
    }

    public PageImpl<CommentDTO> getCommentsByPagination(Integer size, Integer page, String articleId) {
        PageImpl<CommentEntity> byArticleId = commentRepository.findByArticleId(articleId, PageRequest.of(page-1, size ));
        List<CommentDTO> dtoList=new LinkedList<>();
        for (CommentEntity entity : byArticleId.getContent()) {
            dtoList.add(toDTOForPagination(entity));
        }
        return new PageImpl<>(dtoList,PageRequest.of(page- 1, size ),byArticleId.getTotalElements());
    }

    public PageImpl<CommentDTO> getCommentsByFilterWithPagination(Integer size, Integer page, CommentFilterDTO filterDTO) {
        PageImpl<CommentEntity> filter = commentCustomRepository.filter(filterDTO, size, page);
        List<CommentDTO> dtoList=new LinkedList<>();
        for (CommentEntity entity : filter.getContent()) {
            CommentDTO dto=new CommentDTO();
            dto.setId(entity.getId());
            dto.setCreatedDate(entity.getCreatedDate());
            if (entity.getUpdatedDate() != null) {
                dto.setUpdateDate(entity.getUpdatedDate());
            }
            dto.setProfileId(entity.getProfileId());
            dto.setContent(entity.getContent());
            dto.setArticleId(entity.getArticleId());
            dto.setReplyId(entity.getReplyId());
            dto.setVisible(entity.getVisible());
            dtoList.add(dto);
        }
        return new PageImpl<>(dtoList,PageRequest.of(page-1,size),filter.getTotalElements());
    }

    public CommentDTO toDTO(CommentEntity entity) {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setId(entity.getId());
        commentDTO.setProfileId(entity.getProfileId());
        commentDTO.setCreatedDate(entity.getCreatedDate());
        commentDTO.setContent(entity.getContent());
        commentDTO.setReplyId(entity.getReplyId());
        commentDTO.setArticleId(entity.getArticleId());
        return commentDTO;
    }

    public CommentDTO toDTOForPagination(CommentEntity entity) {
        //(id,created_date,update_date,profile(id,name,surname),content,article(id,title),reply_id,)
        CommentDTO dto = new CommentDTO();
        dto.setId(entity.getId());
        dto.setCreatedDate(entity.getCreatedDate());
        if (entity.getUpdatedDate() != null) {
            dto.setUpdateDate(entity.getUpdatedDate());
        }
        ProfileEntity profile = entity.getProfile();
        dto.setProfile(new ProfileDTO(profile.getId(), profile.getName(), profile.getSurname()));
        dto.setContent(entity.getContent());
        ArticleEntity article = entity.getArticle();
        dto.setArticle(new ArticleDTO(article.getId(), article.getTitle()));
        dto.setReplyId(entity.getReplyId());
        return dto;
    }

    public List<CommentDTO> getRepliedCommentListByCommentId(Integer commentId) {
        List<CommentEntity> list = commentRepository.findByReplyId(commentId);
        if (list == null) {
            log.warn("Comments not found CommentService method :: delete");
            throw new AppBadException("Comments not found");
        }
        List<CommentDTO> dtoList = new LinkedList<>();
        for (CommentEntity entity : list) {
            ProfileEntity profile = entity.getProfile();
            CommentDTO commentDTO = toDTO(entity);
            ProfileDTO profileDTO = new ProfileDTO();
            profileDTO.setId(profile.getId());
            profileDTO.setName(profile.getName());
            profileDTO.setSurname(profile.getSurname());
            commentDTO.setProfile(profileDTO);
            dtoList.add(commentDTO);
        }
        return dtoList;
    }
}
