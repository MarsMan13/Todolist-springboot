package com.choigochoigun.todolist.data.repository;

import com.choigochoigun.todolist.data.entity.TagEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TagRepository extends JpaRepository<TagEntity, Long> {
    List<TagEntity> findByTag(String tag);
}
