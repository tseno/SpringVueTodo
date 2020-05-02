package com.example.demo.controller;

import com.example.demo.entity.Task;
import com.example.demo.service.TaskService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(TaskRestController.class)
public class TaskRestControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private TaskService taskService;

    @Test
    public void getTasks() throws Exception {

        ObjectMapper mapper = new ObjectMapper();
        List<Task> taskList = List.of(new Task(1L, "書類の届け出", "2020-05-01", false),
                new Task(2L, "給料日", "2020-05-02", false),
                new Task(3L, "マスクを買う", "2020-05-03", false));
        String json = mapper.writeValueAsString(taskList);

        given(this.taskService.selectAll())
                .willReturn(taskList);

        this.mvc.perform(get("/api/tasks"))
                .andExpect(status().isOk())
                .andExpect(content().json(json));
    }
}
