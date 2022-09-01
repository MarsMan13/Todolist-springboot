package com.choigochoigun.todolist.data.repository;

import com.choigochoigun.todolist.data.entity.TodoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<TodoEntity, Long> {
}
