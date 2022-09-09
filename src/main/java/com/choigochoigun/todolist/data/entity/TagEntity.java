package com.choigochoigun.todolist.data.entity;

import com.choigochoigun.todolist.data.dto.PlanDto;
import com.choigochoigun.todolist.data.dto.TagDto;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name="TODOLIST_TAG")
public class TagEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="TAG_ID")
    private long id;

    @Column(unique = true)
    private String tag;

    @ManyToMany(mappedBy = "tagList")
    private List<PlanEntity> planList = new ArrayList<>();

    public TagDto toDto(){
        return TagDto.builder()
                .id(id)
                .tag(tag)
                .build();
    }

    public TagDto toDto(List<PlanDto> planDtoList){
        TagDto tagDto = this.toDto();
        tagDto.setPlanDtoList(planDtoList);
        return tagDto;
    }
}
