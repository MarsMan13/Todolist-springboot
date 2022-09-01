package com.choigochoigun.todolist.data.dto;

import com.choigochoigun.todolist.data.entity.PlanEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Setter
@Getter
@Builder
public class PlanDto {
    private long id;
    private String title;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
    private List<TodoDto> todoList;
    private List<TagDto> tagList;

    public PlanEntity toEntity() {
        return PlanEntity.builder()
                .id(id)
                .title(title)
                .build();
    }
}
