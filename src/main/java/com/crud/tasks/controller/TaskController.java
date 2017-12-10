package com.crud.tasks.controller;

import com.crud.tasks.domain.TaskDto;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/")
public class TaskController {

    @RequestMapping(method = RequestMethod.GET, value = "tasks")
    public List<TaskDto> getTasks() {
        return new ArrayList<>();
    }

    @RequestMapping(method = RequestMethod.GET, value = "tasks/{id}")
    public TaskDto getTask(@PathVariable("id") Long id) {
        return new TaskDto(id,"test title","test_content");
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "tasks/{id}")
    public void deleteTask(@PathVariable("id") Long id) {

    }

    @RequestMapping(method = RequestMethod.PUT, value = "tasks/{id}")
    public TaskDto updateTask(@PathVariable("id") Long id) {
        return new TaskDto(id,"Edited test title","Test content");
    }

    @RequestMapping(method = RequestMethod.POST, value = "tasks")
    public void createTask(TaskDto taskDto) {

    }
}
