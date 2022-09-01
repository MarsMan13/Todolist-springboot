package com.choigochoigun.todolist.data.repository;

import com.choigochoigun.todolist.data.entity.PlanEntity;
import org.springframework.data.jpa.repository.JpaRepository;

//@Repository
public interface PlanRepository extends JpaRepository<PlanEntity, Long> {
}
