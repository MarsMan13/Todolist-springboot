package com.choigochoigun.todolist.data.dao;

import com.choigochoigun.todolist.data.dto.PlanDto;
import com.choigochoigun.todolist.data.entity.PlanEntity;
import com.choigochoigun.todolist.data.repository.PlanRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Optional;

@Service
@NoArgsConstructor
@Transactional
public class PlanDao {

    PlanRepository planRepository;

    @Autowired
    public PlanDao(PlanRepository planRepository) {
        this.planRepository = planRepository;
    }

    public PlanEntity createPlan(PlanDto planDto){
        PlanEntity planEntity = planDto.toEntity();
        planEntity.setTagList(new ArrayList<>());
        return planRepository.save(planEntity);
    }

    public PlanEntity getPlanById(long id){
        Optional<PlanEntity> planEntityOptional = planRepository.findById(id);
        PlanEntity planEntity = planEntityOptional.orElseThrow();
        return planEntity;
    }

    public void deletePlanById(long id){
        PlanEntity planEntity = planRepository.findById(id).orElseThrow();
        planRepository.delete(planEntity);
    }

    public PlanEntity updatePlanByDto(PlanDto planDto){
        long id = planDto.getId();
        PlanEntity planEntity = planRepository.findById(id).orElseThrow();
        planEntity.setTitle(planDto.getTitle());
        planRepository.save(planEntity);
        return planEntity;
    }
}
