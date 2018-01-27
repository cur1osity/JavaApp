package com.crud.tasks.builders;

import com.crud.tasks.domain.TaskDto;

public class TaskDtoBuilder {

    private TaskDto taskDto = new TaskDto();

    public TaskDtoBuilder id(Long id) {
        taskDto.setId(id);
        return this;
    }

    public TaskDtoBuilder title(String title) {
        taskDto.setTitle(title);
        return this;
    }

    public TaskDtoBuilder content(String content) {
        taskDto.setContent(content);
        return this;
    }

    public TaskDto build() {
        return taskDto;
    }
}