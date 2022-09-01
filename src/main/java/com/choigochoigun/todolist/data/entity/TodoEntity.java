package com.choigochoigun.todolist.data.entity;

import com.choigochoigun.todolist.data.dto.PlanDto;
import com.choigochoigun.todolist.data.dto.TodoDto;
import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name="TODOLIST_TODO")
public class TodoEntity extends BaseTimeEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="TODO_ID")
    private long id;
    @Column
    private String content;
    @ManyToOne
    @JoinColumn(name="PLAN_ID")
    private PlanEntity plan;


    public TodoDto toDto(){
        return TodoDto.builder()
                .id(id)
                .content(content)
                .createdDate(createdDate)
                .modifiedDate(modifiedDate)
                .build();
    }

    public TodoDto toDto(PlanDto planDto){
        TodoDto todoDto = this.toDto();
        todoDto.setPlanDto(planDto);
        return todoDto;
    }
}
