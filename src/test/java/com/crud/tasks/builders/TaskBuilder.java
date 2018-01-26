package com.crud.tasks.builders;

import com.crud.tasks.domain.Task;

public class TaskBuilder {

    private Task task = new Task();

    public TaskBuilder id(Long id) {
        task.setId(id);
        return this;
    }

    public TaskBuilder title(String title) {
        task.setTitle(title);
        return this;
    }

    public TaskBuilder content(String content) {
        task.setContent(content);
        return this;
    }

    public Task build() {
        return task;
    }
}
