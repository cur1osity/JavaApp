package com.crud.tasks.service;

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
        return repository.save(task);
    }

    public Task updateTaskWithId(Long id, Task task) {

        if(repository.existsById(id)) {

            task.setId(id);

            return repository.save(task);
        }

        return null;
    }

    public void deleteTask(Long id) {
        repository.deleteById(id);
    }

    public void deleteAllTask() {
        repository.deleteAllInBatch();
    }

}
