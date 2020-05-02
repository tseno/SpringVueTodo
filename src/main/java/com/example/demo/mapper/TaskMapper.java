package com.example.demo.mapper;

import com.example.demo.entity.Task;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface TaskMapper {
    List<Task> selectAll();

    Task select(long id);

    int insert(Task task);

    int update(Task task);

    int delete(long id);
}
