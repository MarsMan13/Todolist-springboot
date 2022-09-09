package com.choigochoigun.todolist.data.dao;

import com.choigochoigun.todolist.data.dto.TodoDto;
import com.choigochoigun.todolist.data.entity.PlanEntity;
import com.choigochoigun.todolist.data.entity.TodoEntity;
import com.choigochoigun.todolist.data.repository.PlanRepository;
import com.choigochoigun.todolist.data.repository.TodoRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@NoArgsConstructor
@Transactional
public class TodoDao {

    private TodoRepository todoRepository;
    private PlanRepository planRepository;

    @Autowired
    public TodoDao(TodoRepository todoRepository, PlanRepository planRepository){
        this.todoRepository = todoRepository;
        this.planRepository = planRepository;
    }

    public TodoEntity createTodo(long planId, TodoDto todoDto){
        PlanEntity planEntity = planRepository.findById(planId).orElseThrow();
        TodoEntity todoEntity = todoDto.toEntry();
        todoEntity.setPlan(planEntity);
        todoRepository.save(todoEntity);
        return todoEntity;
    }

    public TodoEntity setPlan(TodoEntity todoEntity, PlanEntity planEntity) {
        todoEntity.setPlan(planEntity);
        return todoEntity;
    }

    public void deleteTodoById(long id){
        TodoEntity todoEntity = todoRepository.findById(id).orElseThrow();
        todoRepository.delete(todoEntity);
    }

    public TodoEntity updateTodoByDto(TodoDto todoDto){
        long id = todoDto.getId();
        TodoEntity todoEntity = todoRepository.findById(id).orElseThrow();
        todoEntity.setContent(todoDto.getContent());
        todoRepository.save(todoEntity);
        return todoEntity;
    }
}
