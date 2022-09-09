package com.choigochoigun.todolist.controller;

import com.choigochoigun.todolist.data.dto.PlanDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(
        properties = {
                "testName=TodoListController_Test",
        },
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@Transactional
@AutoConfigureMockMvc
public class TodoListControllerSpringBootTest {

    private final String rootPath = "/api/v1/todolist-api";

    @Value("${testName}")
    private String testName;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Plan을 생성하고 그 Id로 조회하기")
    public void Plan을_생성하고_Id로_조회하기() throws Exception {
        // GIVEN
        PlanDto planDto = PlanDto.builder()
                .id(0)
                .title("First Plan")
                .build();
        ObjectMapper objectMapper = new ObjectMapper();
        String content = objectMapper.writeValueAsString(planDto);
        // Plan 생성하기.
        mockMvc.perform(
                post(rootPath + "/plan")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value(planDto.getTitle()))
                .andDo(print());

        // ID로 조회하기.
        long id = 1L;
        mockMvc.perform(
                get(rootPath + "/plan/" + id)
        ).andExpect(jsonPath("$.id").value(id)).andDo(print());
    }
}
