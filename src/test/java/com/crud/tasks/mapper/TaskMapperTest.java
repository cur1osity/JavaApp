package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(MockitoJUnitRunner.class)
public class TaskMapperTest {

    @InjectMocks
    private TaskMapper mapper;

    @Test
    public void should_mapToTask(){
        // Given
        TaskDto taskDto = new TaskDto(1L, "Test", "TestX");

        // When
        Task task = mapper.mapToTask(taskDto);

        // Then
        Assert.assertEquals(1L, task.getId().longValue());
        Assert.assertEquals("Test", task.getTitle());
        Assert.assertEquals("TestX", task.getContent());
    }

    @Test
    public void should_mapToTaskDto(){
        // Given
        Task task = new Task(1L, "Test", "TestX");

        // When
        TaskDto taskDto = mapper.mapToTaskDto(task);

        // Then
        Assert.assertEquals(1L, taskDto.getId().longValue());
        Assert.assertEquals("Test", taskDto.getTitle());
        Assert.assertEquals("TestX", taskDto.getContent());
    }

    @Test
    public void should_mapToTaskDtoList(){
        // Given
        List<Task> taskList = new ArrayList<>();
        taskList.add(new Task(1L, "Test1", "TestX1"));
        taskList.add(new Task(2L, "Test2", "TestX2"));
        taskList.add(new Task(3L, "Test3", "TestX3"));

        // When
        List<TaskDto> taskDtos =  mapper.mapToTaskDtoList(taskList);

        // Then
        assertNotNull(taskDtos);
        assertEquals(3, taskDtos.size());
        assertEquals("Test3", taskDtos.get(2).getTitle());
        assertEquals(1, taskDtos.get(0).getId().longValue());
    }
}

