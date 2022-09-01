package com.choigochoigun.todolist.data.dto;

import com.choigochoigun.todolist.data.entity.TodoEntity;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class TodoDto {

    private long id;
    private String content;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
    private PlanDto planDto;

    public TodoEntity toEntry(){
        return TodoEntity.builder()
                .id(id)
                .content(content)
                .build();
    }
}
