package com.example.kun_uz_lesson1.service;

import com.example.kun_uz_lesson1.entity.ArticleTypesEntity;
import com.example.kun_uz_lesson1.repository.ArticleTypesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleTypesService {
    @Autowired
    private ArticleTypesRepository articleTypesRepository;

    public void create(String articleId, List<Integer> typesIdList) {
        for (Integer typeId : typesIdList) {
            create(articleId, typeId);
        }
    }

    public void create(String articleId, Integer typesId) {
        ArticleTypesEntity entity = new ArticleTypesEntity();
        entity.setArticleId(articleId);
        entity.setTypesId(typesId);
        articleTypesRepository.save(entity);
    }

    public void merge(String articleId, List<Integer> typesIdList) {
        // create
        // [] old
        // [1,2,3,4,5] new
        List<ArticleTypesEntity> entities = articleTypesRepository.findByArticleId(articleId);
//        if (entities == null || entities.size() == 0) {
//            return;
//        }
        int count = 0;
        for (ArticleTypesEntity entity : entities) {
            if (!typesIdList.contains(entity.getTypesId())) {
                articleTypesRepository.deleteById(entity.getId());
                count++;
            }
        }

        if (count == 0 && entities.size() == typesIdList.size()) {
            return;
        }
        // update
        //[1,2,3,4,5] - old
        //[7,5] - new
        for (Integer typesId : typesIdList) {
            List<ArticleTypesEntity> articleTypesList = articleTypesRepository.findByTypesIdOrderByCreatedDateDesc(typesId);
            if (articleTypesList.isEmpty()) {
                ArticleTypesEntity entity = new ArticleTypesEntity();
                entity.setArticleId(articleId);
                entity.setTypesId(typesId);
                articleTypesRepository.save(entity);
            }
        }
    }

    public List<ArticleTypesEntity> findByArticleTypeId(Integer id) {
        return articleTypesRepository.findByTypesIdOrderByCreatedDateDesc(id);
    }
}
