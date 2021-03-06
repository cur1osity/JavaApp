package com.crud.tasks.service;

import com.crud.tasks.controller.TaskNotFoundException;
import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository repository;

    public List<Task> getAllTasks() {
        return repository.findAll();
    }

    public Optional<Task> getTask(Long id) {
        return repository.findById(id);
    }

    public Task saveTask (final Task task) {
        task.setId(null);
        return repository.save(task);
    }

    public Task updateTaskWithId(Long id, Task task) throws TaskNotFoundException {

        if(repository.existsById(id)) {

            task.setId(id);

            return repository.save(task);
        }

        throw new TaskNotFoundException();
    }

    public void deleteTask(Long id) {
        repository.deleteById(id);
    }

    public void deleteAllTask() {
        repository.deleteAllInBatch();
    }

}
