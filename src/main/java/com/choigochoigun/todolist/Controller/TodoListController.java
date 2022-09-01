package com.choigochoigun.todolist.Controller;

import com.choigochoigun.todolist.data.dto.PlanDto;
import com.choigochoigun.todolist.data.dto.TodoDto;
import com.choigochoigun.todolist.service.TodoListService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/todolist-api")
public class TodoListController {

    private TodoListService todoListService;

    public TodoListController(TodoListService listService) {
        this.todoListService = listService;
    }

    // GET A PLAN
    @GetMapping("/plan/{planId}")
    public PlanDto getList(@PathVariable long planId){
        return null;
    }

    // POST NEW PLAN
    @PostMapping("/plan")
    public PlanDto createList(@Valid @RequestBody PlanDto planDto) {
        return todoListService.createPlan(planDto);
    }

    // SET PLAN WITH TAG
    @GetMapping("/tag/{planId}/{tagName}")
    public ResponseEntity setTag(@PathVariable long planId, @PathVariable String tagName){
        PlanDto planDto = todoListService.setTag(planId, tagName);
        return new ResponseEntity(
                planDto == null ? "SUCCESS" : "FAIL",
                planDto == null ? HttpStatus.INTERNAL_SERVER_ERROR : HttpStatus.OK
                );
    }

    // CREATE NEW TO_DO AND CONNECT IT WITH parent PLAN
    @PostMapping("/todo/{planId}")
    public TodoDto createTodo(@PathVariable long planId, @Valid @RequestBody TodoDto todoDto){
        TodoDto todoDtoResult = todoListService.createTodo(planId, todoDto);
        return todoDtoResult;
    }
}