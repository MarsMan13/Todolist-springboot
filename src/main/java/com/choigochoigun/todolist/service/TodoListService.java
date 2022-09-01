package com.choigochoigun.todolist.service;

import com.choigochoigun.todolist.data.dao.PlanDao;
import com.choigochoigun.todolist.data.dao.TagDao;
import com.choigochoigun.todolist.data.dao.TodoDao;
import com.choigochoigun.todolist.data.dto.PlanDto;
import com.choigochoigun.todolist.data.dto.TagDto;
import com.choigochoigun.todolist.data.dto.TodoDto;
import com.choigochoigun.todolist.data.entity.PlanEntity;
import com.choigochoigun.todolist.data.entity.TagEntity;
import com.choigochoigun.todolist.data.entity.TodoEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TodoListService {
    private final PlanDao planDao;
    private final TodoDao todoDao;
    private final TagDao tagDao;

    public TodoListService(PlanDao planDao, TodoDao todoDao, TagDao tagDao) {
        this.planDao = planDao;
        this.todoDao = todoDao;
        this.tagDao = tagDao;
    }

    public PlanDto createPlan(PlanDto planDto){
        return planDao.createPlan(planDto).toDto();
    }

    @Transactional
    public PlanDto setTag(long planId, String tagName) {
        TagEntity tagEntity = tagDao.createTagByName(tagName);
        PlanEntity planEntity = planDao.getPlanById(planId);
        planEntity = tagDao.setPlan(tagEntity, planEntity);
        return planEntity.toDto();
    }

    @Transactional
    public TodoDto createTodo(long planId, TodoDto todoDto) {
        PlanEntity planEntity = planDao.getPlanById(planId);
        TodoEntity todoEntity = todoDao.createTodo(planId, todoDto);
        todoEntity = todoDao.setPlan(todoEntity, planEntity);
        return todoEntity.toDto();
    }
}
