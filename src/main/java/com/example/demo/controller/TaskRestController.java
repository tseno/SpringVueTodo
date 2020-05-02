package com.example.demo.controller;

import com.example.demo.entity.Task;
import com.example.demo.service.TaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/tasks")
@Slf4j
public class TaskRestController {

    @Autowired
    TaskService taskService;

    @GetMapping
    public List<Task> getTasks() {
        return taskService.selectAll();
    }

    @GetMapping("/{id}")
    public Task getTask(@PathVariable Long id) {
        return taskService.select(id);
    }

    @PostMapping()
    public int postTask(@RequestBody Task task) {
        return taskService.insert(task);
    }

    @PutMapping("/{id}")
    public int putTask(@PathVariable Long id, @RequestBody Task task) {
        return taskService.update(task);
    }

    @DeleteMapping("/{id}")
    public int deleteTask(@PathVariable Long id) {
        return taskService.delete(id);
    }
}
