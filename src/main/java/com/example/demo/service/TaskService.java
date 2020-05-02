package com.example.demo.service;

import com.example.demo.entity.Task;
import com.example.demo.mapper.TaskMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    @Autowired
    TaskMapper taskMapper;

    public List<Task> selectAll() {
        return taskMapper.selectAll();
    }

    public Task select(long id) {
        return taskMapper.select(id);
    }

    public int insert(Task task) {
        return taskMapper.insert(task);
    }

    public int update(Task task) {
        return taskMapper.update(task);
    }

    public int delete(long id) {
        return taskMapper.delete(id);
    }
}
