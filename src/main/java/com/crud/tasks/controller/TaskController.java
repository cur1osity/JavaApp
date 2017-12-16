package com.crud.tasks.controller;

import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/tasks")
public class TaskController {

    @Autowired
    private DbService service;

    @Autowired
    private TaskMapper taskMapper;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<TaskDto> getTasks() {
        return taskMapper.mapToTaskDtoList(service.getAllTasks());
    }

    @GetMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public TaskDto getTask(@PathVariable long id) throws TaskNotFoundException {
        return taskMapper.mapToTaskDto(service.getTask(id).orElseThrow(TaskNotFoundException::new));
    }

    @DeleteMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public void deleteTask(@PathVariable long id) {
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TaskDto updateTask(@PathVariable long id) {
        return new TaskDto(id,"Edited test title","Test content");
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createTask(TaskDto taskDto) {
    }
}
