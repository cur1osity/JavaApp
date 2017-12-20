package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


@RestController
@RequestMapping("/v1/tasks")
public class TaskController {

    @Autowired
    private TaskService service;

    @Autowired
    private TaskMapper taskMapper;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<TaskDto> getTasks() throws TaskNotFoundException {
        List<Task> allTasks = service.getAllTasks();

        if (allTasks.isEmpty()) {
            throw new TaskNotFoundException();
        }
        return taskMapper.mapToTaskDtoList(allTasks);
    }

    @GetMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public TaskDto getTask(@PathVariable Long id) throws TaskNotFoundException {
        final Task task = service.getTask(id).orElseThrow(TaskNotFoundException::new);
        return taskMapper.mapToTaskDto(task);
    }

    @DeleteMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public void deleteTask(@PathVariable long id) {
        service.deleteTask(id);
    }


    @PutMapping(value = {"/{id}"}, consumes = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public TaskDto updateTask(@PathVariable Long id, @RequestBody TaskDto taskDto) throws TaskNotFoundException {

        Task task = taskMapper.mapToTask(taskDto);

        Task taskAfterUpdate = service.updateTaskWithId(id, task);

        return taskMapper.mapToTaskDto(taskAfterUpdate);
    }


//    @PostMapping(consumes = APPLICATION_JSON_VALUE)
//    @ResponseStatus(HttpStatus.CREATED)
//    public void createTask(@RequestBody TaskDto taskDto) throws TaskConflictException {
//
//        final Task taskFromJSON = taskMapper.mapToTask(taskDto);
//
//        if (taskFromJSON.getId() != null) {
//
//            Task task = service.getTask(taskFromJSON.getId()).orElse(service.saveTask(taskFromJSON));
//
//            if (task.getId() == taskFromJSON.getId()) {
//
//                throw new TaskConflictException();
//            }
//
//        } else {
//
//            service.saveTask(taskFromJSON);
//        }
//    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void createTask(@RequestBody TaskDto taskDto) throws TaskConflictException {

        final Task taskFromJSON = taskMapper.mapToTask(taskDto);

        if (taskFromJSON.getId() != null) {

            if (service.isTaskExist(taskFromJSON.getId())) {

                throw new TaskConflictException();
            }

            service.saveTask(taskFromJSON);

        } else {

            service.saveTask(taskFromJSON);
        }
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public void deleteTasks() {
        service.deleteAllTask();
    }
}

