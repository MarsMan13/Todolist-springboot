package com.choigochoigun.todolist.controller;

import com.choigochoigun.todolist.data.dto.PlanDto;
import com.choigochoigun.todolist.data.dto.TodoDto;
import com.choigochoigun.todolist.service.TodoListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/todolist-api")
public class TodoListController {

    private TodoListService todoListService;

    @Autowired
    public TodoListController(TodoListService listService) {
        this.todoListService = listService;
    }

    @GetMapping("/test")
    public String test(){
        return "hello";
    }
    // GET A PLAN
    @GetMapping("/plan/{planId}")
    public PlanDto getPlan(@PathVariable long planId){
        return todoListService.getPlanById(planId);
    }

    // POST NEW PLAN
    @PostMapping("/plan")
    public PlanDto createList(@Valid @RequestBody PlanDto planDto) {
        return todoListService.createPlan(planDto);
    }

    // SET PLAN WITH TAG
    @GetMapping("/tag/{planId}/{tagName}")
    public ResponseEntity<String> setTag(@PathVariable long planId, @PathVariable String tagName){
        PlanDto planDto = todoListService.setTag(planId, tagName);
        return new ResponseEntity<String>(
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

    // DELETE MAPPING
    //// DELETE PLAN
    @DeleteMapping("/plan/{planId}")
    public ResponseEntity deletePlan(@PathVariable long planId){
        return null;
    }
    //// DELETE TODO
    @DeleteMapping("/todo/{todoId}")
    public ResponseEntity deleteTodo(@PathVariable long todoId){
        return null;
    }
    //// DELETE TAG
    @DeleteMapping("/tag/{tagId}")
    public ResponseEntity deleteTag(@PathVariable long tagId){
        return null;
    }
}