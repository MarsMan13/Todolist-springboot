package com.choigochoigun.todolist.data.entity;

import com.choigochoigun.todolist.data.dto.PlanDto;
import com.choigochoigun.todolist.data.dto.TodoDto;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Builder
@Table(name="TODOLIST_PLAN")
public class PlanEntity extends BaseTimeEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PLAN_ID")
    private long id;

    private String title;

    @OneToMany(mappedBy = "plan")
    private List<TodoEntity> todoEntityList = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "PLAN_TAG",
            joinColumns = @JoinColumn(name = "PLAN_ID"),
            inverseJoinColumns = @JoinColumn(name = "TAG_ID")
    )
    private List<TagEntity> tagList = new ArrayList<>();

    public PlanDto toDto() {
        PlanDto planDto = PlanDto.builder()
                .id(id)
                .title(title)
                .createdDate(createdDate)
                .modifiedDate(modifiedDate)
                .build();
        return planDto;
    }

    public PlanDto toDto(List<TodoDto> todoDtoList){
        PlanDto planDto = this.toDto();
        planDto.setTodoList(todoDtoList);
        return planDto;
    }
}
