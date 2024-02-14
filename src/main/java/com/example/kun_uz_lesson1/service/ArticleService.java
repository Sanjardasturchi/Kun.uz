package com.example.kun_uz_lesson1.service;

import com.example.kun_uz_lesson1.config.CustomUserDetails;
import com.example.kun_uz_lesson1.dto.*;
import com.example.kun_uz_lesson1.entity.*;
import com.example.kun_uz_lesson1.enums.AppLanguage;
import com.example.kun_uz_lesson1.enums.ArticleStatus;
import com.example.kun_uz_lesson1.enums.ProfileRole;
import com.example.kun_uz_lesson1.exp.AppBadException;
import com.example.kun_uz_lesson1.repository.ArticleRepository;
import com.example.kun_uz_lesson1.repository.ArticleTypeRepository;
import com.example.kun_uz_lesson1.repository.AttachRepository;
import com.example.kun_uz_lesson1.util.JWTUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
@Slf4j
@Service
public class ArticleService {
    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private AttachService attachService;
    @Autowired
    private ArticleTypesService articleTypesService;
    @Autowired
    private ArticleTagService articleTagService;


    public void create(ArticleCreateDTO dto) {
        CustomUserDetails userDetails =(CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Integer profileId = userDetails.getId();

        ArticleEntity article = new ArticleEntity();
        article.setTitleUz(dto.getTitleUz());
        article.setTitleRu(dto.getTitleRu());
        article.setTitleEn(dto.getTitleEn());
        article.setDescriptionUz(dto.getDescriptionUz());
        article.setDescriptionRu(dto.getDescriptionRu());
        article.setDescriptionEn(dto.getDescriptionEn());
        article.setContentUz(dto.getContentUz());
        article.setContentRu(dto.getContentRu());
        article.setContentEn(dto.getContentEn());

        article.setRegionId(dto.getRegionId());
        article.setModeratorId(profileId);
        article.setPhotoId(dto.getPhotoId());
        article.setCreatedDate(LocalDateTime.now());
        articleRepository.save(article);

        articleTypesService.merge(article.getId(), dto.getArticleType());

    }
    public ArticleDTO update(String articleId, ArticleCreateDTO dto) {
        CustomUserDetails userDetails =(CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Integer profileId = userDetails.getId();
        Optional<ArticleEntity> optional = articleRepository.findById(articleId);
        // check
        ArticleEntity article = optional.get();
        article.setTitle(dto.getTitleUz());
        article.setDescription(dto.getDescriptionUz());
        article.setContent(dto.getContentUz());

        article.setRegionId(dto.getRegionId());
        article.setModeratorId(profileId);
        article.setCategoryId(dto.getCategoryId());
        article.setPhotoId(dto.getPhotoId());
        articleRepository.save(article);

        articleTypesService.merge(article.getId(), dto.getArticleType());
        Optional<ArticleEntity> entity = articleRepository.findById(articleId);
        return toDTO(entity.get());
    }


    public String delete(String id) {

        if (articleRepository.makeDeleted(id) != 0) {
            return "DONE";
        }
        throw new AppBadException("ArticleType not found");
    }

    public PageImpl<ArticleDTO> allByPagination(Integer page, Integer size, String jwt) {
        JwtDTO decode = JWTUtil.decode(jwt);
        if (!decode.getRole().equals(ProfileRole.ROLE_ADMIN)) {
            throw new AppBadException("You can not");
        }
        Page<ArticleEntity> all = articleRepository.findAll(PageRequest.of(page - 1, size));
        return new PageImpl<>(toDTOListFromIterable(all.getContent()), PageRequest.of(page - 1, size), all.getTotalElements());
    }

    public List<ArticleDTO> getAll() {
        return toDTOListFromIterable(articleRepository.all());
    }

    private List<ArticleDTO> toDTOListFromIterable(Iterable<ArticleEntity> entities) {
        List<ArticleDTO> dtoList = new LinkedList<>();
        for (ArticleEntity entity : entities) {
            if (entity.getVisible()) {
                dtoList.add(toDTO(entity));
            }
        }
        return dtoList;
    }
    private ArticleDTO toDTOByLanguage(ArticleEntity entity, AppLanguage language) {
        ArticleDTO dto = new ArticleDTO();
        dto.setId(entity.getId());
        if (language.equals(AppLanguage.uz)) {
            dto.setContent(entity.getContentUz());
            dto.setTitle(entity.getTitleUz());
            dto.setDescription(entity.getDescriptionUz());
        }else if (language.equals(AppLanguage.ru)) {
            dto.setContent(entity.getContentRu());
            dto.setTitle(entity.getTitleRu());
            dto.setDescription(entity.getDescriptionRu());
        }else {
            dto.setContent(entity.getContentEn());
            dto.setTitle(entity.getTitleEn());
            dto.setDescription(entity.getDescriptionEn());
        }
        dto.setPhotoId(entity.getPhotoId());
        dto.setCategoryId(entity.getCategory().getId());
        dto.setModeratorId(entity.getModerator().getId());
        dto.setRegionId(entity.getRegion().getId());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setPublishedDate(entity.getPublishedDate());
//        dto.setPublisherId(entity.getPublisher().getId());
        dto.setSharedCount(entity.getSharedCount());
        dto.setViewCount(entity.getViewCount());
        return dto;
    }

    public ArticleDTO changeStatusById(String id, ArticleStatus status) {
        articleRepository.updateStatus(status, id);
        Optional<ArticleEntity> entity = articleRepository.findById(id);
        if (entity.isEmpty()) {
            throw new AppBadException("Article not found");
        }
        ArticleEntity article = entity.get();
        return null;
    }

    public ArticleTypeEntity[] articleTypeToEntityArray(ArticleTypeDTO[] dtos) {
        ArticleTypeEntity[] entities = new ArticleTypeEntity[dtos.length];
        for (int i = 0; i < dtos.length; i++) {
            entities[i] = articleTypeToEntity(dtos[i]);
        }
        return entities;
    }

    private ArticleTypeEntity articleTypeToEntity(ArticleTypeDTO dto) {
        ArticleTypeEntity entity = new ArticleTypeEntity();
        entity.setId(dto.getId());
        return entity;
    }

    public ArticleTypeDTO[] articleTypeToDTOArray(ArticleTypeEntity[] entities) {
        ArticleTypeDTO[] dtos = new ArticleTypeDTO[entities.length];
        for (int i = 0; i < entities.length; i++) {
            dtos[i] = articleTypeToDTO(entities[i]);
        }
        return dtos;
    }

    private ArticleTypeDTO articleTypeToDTO(ArticleTypeEntity entity) {
        ArticleTypeDTO dto = new ArticleTypeDTO();
        dto.setId(entity.getId());
        return dto;
    }

    public List<ArticleShortInfo> getLastFive(Integer id) {
        return getArticleDTOLastByTypeIdOrderByCreatedDateLimitN(id,5);
    }
    public List<ArticleShortInfo> getLastThree(Integer id) {
        return getArticleDTOLastByTypeIdOrderByCreatedDateLimitN(id,3);
    }

    private List<ArticleShortInfo> getArticleDTOLastByTypeIdOrderByCreatedDateLimitN(Integer id,Integer limit) {
        List<ArticleTypesEntity> articleTypesEntities = articleTypesService.findByArticleTypeId(id);
        if (articleTypesEntities == null||articleTypesEntities.size()==0) {
            log.warn("Article Types not found method :: getArticleDTOLastByTypeIdOrderByCreatedDateLimitN");
            throw new AppBadException("Article Types not found");
        }
        List<ArticleShortInfo> dtoList=new LinkedList<>();
        int count =0;
        for (ArticleTypesEntity articleTypesEntity : articleTypesEntities) {
            Optional<ArticleEntity> optional = articleRepository.findById(articleTypesEntity.getArticleId());
            if (optional.isEmpty()) {
                continue;
            }
            dtoList.add(toShortDTO(optional.get()));
            count++;
            if (count==limit) {
                break;
            }
        }
        return dtoList;
    }



    public List<ArticleShortInfo> getLastEightIdNotIncludedInGivenList( ArticleIdListDTO list) {
        List<String> idList = list.getList();
        Iterable<ArticleEntity> byOrderByCreatedDateDesc = articleRepository.findByOrderByCreatedDateDesc();
        List<ArticleShortInfo>dtoList=new LinkedList<>();
        for (ArticleEntity article : byOrderByCreatedDateDesc) {
            if (!idList.contains(article.getId())) {
                dtoList.add(toShortDTO(article));
                if (dtoList.size()==8){
                    break;
                }
            }
        }
        return dtoList;
    }

    public String increaseArticleViewCount(String id) {
        Optional<ArticleEntity> entity = articleRepository.findById(id);
        if (entity.isEmpty()) {
            log.warn("Article not found method :: increaseArticleViewCount");
            throw new AppBadException("Article not found");
        }
        if (entity.get().getViewCount() == null) {
            articleRepository.increaseArticleViewCount(id,1);
            return "DONE";
        }
        articleRepository.increaseArticleViewCount(id,entity.get().getViewCount()+1);
        return "DONE";
    }

    public String increaseArticleSharedCount(String id) {
        Optional<ArticleEntity> entity = articleRepository.findById(id);
        if (entity.isEmpty()) {
            log.warn("Article not found method :: increaseArticleShareCount");
            throw new AppBadException("Article not found");
        }
        if (entity.get().getSharedCount() == null) {
             articleRepository.increaseArticleSharedCount(id,1);
             return "DONE";
        }
        articleRepository.increaseArticleSharedCount(id,entity.get().getSharedCount()+1);
        return "DONE";
    }

    public List<ArticleShortInfo> getLastFourByTypesAndExceptGivenArticleId(Integer typeId, String articleId) {
        List<ArticleShortInfo> lastFive = getLastFive(typeId);
        for (ArticleShortInfo articleDTO : lastFive) {
            if (articleDTO.getId().equals(articleId)) {
                lastFive.remove(articleDTO);
                break;
            }
        }
        if (lastFive.size()==5) {
            lastFive.remove(4);
        }
        return lastFive;
    }

    private ArticleDTO toDTO(ArticleEntity entity) {
        ArticleDTO dto = new ArticleDTO();
        dto.setId(entity.getId());
        dto.setContent(entity.getContent());
        dto.setDescription(entity.getDescription());
        dto.setPhotoId(entity.getPhotoId());
        dto.setTitle(entity.getTitle());
        dto.setCategoryId(entity.getCategory().getId());
        dto.setModeratorId(entity.getModerator().getId());
        dto.setRegionId(entity.getRegion().getId());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setPublishedDate(entity.getPublishedDate());
//        dto.setPublisherId(entity.getPublisher().getId());
        dto.setSharedCount(entity.getSharedCount());
        dto.setViewCount(entity.getViewCount());
        return dto;
    }

    private ArticleShortInfo toShortDTO(ArticleEntity entity) {
        AttachEntity attachEntity = attachService.get(entity.getPhotoId());

        ArticleShortInfo dto = new ArticleShortInfo();
        dto.setId(entity.getId());
        dto.setDescription(entity.getDescription());
        dto.setTitle(entity.getTitle());
        dto.setPublishedDate(entity.getPublishedDate());
        dto.setPhoto(attachService.toDTO(attachEntity));
        return dto;
    }

    public List<ArticleShortInfo> getFourMostReadArticles() {
        return articleRepository.findByOrderByViewCountDesc().stream().limit(4).toList();
    }

    public List<ArticleShortInfo> getFourArticlesByTagName(String tagName) {
        List<ArticleTagDTO> allByTag = articleTagService.getAllByTag(tagName);
        List<ArticleShortInfo> list=new LinkedList<>();
        int count=0;
        for (ArticleTagDTO dto : allByTag) {
            if (count==4) {
                break;
            }
            list.add(toShortDTO(articleRepository.findById(dto.getArticleId()).get()));
            count++;
        }
        return list;
    }
}
