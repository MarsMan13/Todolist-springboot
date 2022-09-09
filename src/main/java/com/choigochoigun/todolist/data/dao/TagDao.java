package com.choigochoigun.todolist.data.dao;

import com.choigochoigun.todolist.data.dto.PlanDto;
import com.choigochoigun.todolist.data.dto.TagDto;
import com.choigochoigun.todolist.data.entity.PlanEntity;
import com.choigochoigun.todolist.data.entity.TagEntity;
import com.choigochoigun.todolist.data.repository.PlanRepository;
import com.choigochoigun.todolist.data.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TagDao {

    private TagRepository tagRepository;
    private PlanRepository planRepository;
    @Autowired
    public TagDao(TagRepository tagRepository, PlanRepository planRepository){
        this.tagRepository = tagRepository;
        this.planRepository = planRepository;
    }

    public TagEntity getTagByName(String tagName) {
        List<TagEntity> tagEntity = tagRepository.findByTag(tagName);
        if(tagEntity == null || tagEntity.size() == 0)
            return null;
        return tagEntity.get(0);
    }

    public TagEntity createTagByName(String tagName){
        TagEntity tagEntity = getTagByName(tagName);
        if(tagEntity == null){
            TagEntity newTagEntity = TagEntity.builder()
                    .id(0L)
                    .tag(tagName)
                    .planList(new ArrayList<>())
                    .build();
            tagEntity = tagRepository.save(newTagEntity);
        }
        return tagEntity;
    }

    public PlanEntity setPlan(TagEntity tagEntity, PlanEntity planEntity) {
        // FOR GETTING TAG ENTITY
//        if(tagEntity == null || planEntity == null)
//            return null;
        // FOR GETTING PLAN ENTITY
        tagEntity.getPlanList().add(planEntity);
        planEntity.getTagList().add(tagEntity);
        return planEntity;
    }
    public void deleteTagById(long id){
        TagEntity tagEntity = tagRepository.findById(id).orElseThrow();
        tagRepository.delete(tagEntity);
    }

    public TagEntity updateTagByDto(TagDto tagDto){
        long id = tagDto.getId();
        TagEntity tagEntity = tagRepository.findById(id).orElseThrow();
        tagEntity.setTag(tagDto.getTag());
        tagRepository.save(tagEntity);
        return tagEntity;
    }

    public void deleteTagFromPlan(long planId, long tagId){
        PlanEntity planEntity = planRepository.findById(planId).orElseThrow();
        TagEntity tagEntity = tagRepository.findById(tagId).orElseThrow();
        if(planEntity.getTagList() != null && planEntity.getTagList().contains(tagEntity))
            planEntity.getTagList().remove(tagEntity);
        if(tagEntity.getPlanList() != null && tagEntity.getPlanList().contains(planEntity))
            tagEntity.getPlanList().remove(planEntity);
    }
}
