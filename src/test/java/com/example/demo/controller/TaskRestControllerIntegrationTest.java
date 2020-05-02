package com.example.demo.controller;

import com.example.demo.entity.Task;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("unit")
@Slf4j
public class TaskRestControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void getTasks() throws Exception {

        ObjectMapper mapper = new ObjectMapper();
        List<Task> taskList = List.of(new Task(1L, "書類の届け出テスト", "2020-05-31", false),
                new Task(2L, "給料日テスト", "2020-05-25", false),
                new Task(3L, "マスクを買うテスト", "2020-05-01", false));
        String expectedJSONString = mapper.writeValueAsString(taskList);

        String actualJSON = this.restTemplate.getForObject("http://localhost:" + port + "/api/tasks",
                String.class);
        log.info(actualJSON);

        JSONAssert.assertEquals(expectedJSONString, actualJSON, false);
    }
}
