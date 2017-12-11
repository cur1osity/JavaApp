package com.crud.tasks.controller;

import com.crud.tasks.domain.TaskDto;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/tasks")
public class TaskController {

    @RequestMapping(method = RequestMethod.GET)
    public List<TaskDto> getTasks() {
        return new ArrayList<>();
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public TaskDto getTask(@PathVariable Long id) {
        return new TaskDto(id,"test title","test_content");
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/{id}")
    public void deleteTask(@PathVariable Long id) {
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/{id}")
    public TaskDto updateTask(@PathVariable Long id) {
        return new TaskDto(id,"Edited test title","Test content");
    }

    @RequestMapping(method = RequestMethod.POST)
    public void createTask(TaskDto taskDto) {
    }
}
