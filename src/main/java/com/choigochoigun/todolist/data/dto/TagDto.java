package com.choigochoigun.todolist.data.dto;

import com.choigochoigun.todolist.data.entity.TagEntity;
import lombok.*;

import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class TagDto {
    private long id;
    private String tag;
    private List<PlanDto> planDtoList = new ArrayList<>();

    public TagEntity toEntity(){
        return TagEntity.builder()
                .id(id)
                .tag(tag)
                .planList(null)
                .build();
    }
}
