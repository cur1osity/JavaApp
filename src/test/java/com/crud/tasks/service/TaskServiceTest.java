package com.crud.tasks.service;

import com.crud.tasks.builders.TaskBuilder;
import com.crud.tasks.controller.TaskNotFoundException;
import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class TaskServiceTest {

    private static final Long TASK1_ID = 1L;
    private static final Task TASK1 = new TaskBuilder()
            .id(TASK1_ID)
            .title("Test1")
            .content("Test1")
            .build();

    private static final Task TASK2 = new TaskBuilder()
            .id(2L)
            .title("Test2")
            .content("Test2")
            .build();

    private static final Task NEW_TASK = new TaskBuilder()
            .title("Test2")
            .content("Test2")
            .build();

    @InjectMocks
    private TaskService taskService;

    @Mock
    private TaskRepository repository;

    private ArgumentCaptor<Task> anyTask = ArgumentCaptor.forClass(Task.class);

    @Test
    public void whenFindingTasksItShouldReturnAllTasks() {

        // Given
        given(repository.findAll()).willReturn(Arrays.asList(TASK1, TASK2));
        // When
        List<Task> tasks = taskService.getAllTasks();
        // Then
        assertThat(tasks)
                .containsOnly(TASK1, TASK2);
    }

    @Test
    public void whenFindingTaskWithGivenIdItShouldReturnTaskWithGivenId() {
        // Given
        given(repository.findById(TASK1_ID)).willReturn(Optional.of(TASK1));
        // When
        Optional<Task> task = taskService.getTask(TASK1_ID);
        // Then
        assertThat(task).isEqualTo(Optional.of(TASK1));
    }

    @Test
    public void whenAddingTaskItShouldReturnTheSavedTask() {
        // Given
        given(repository.save(NEW_TASK)).willReturn(TASK1);
        // When
        assertThat(taskService.saveTask(NEW_TASK))
                // Then
                .isSameAs(TASK1);
    }

    @Test
    public void whenAddingTaskItShouldMakeSureNoIDIsPassed() {
        // Given
        taskService.saveTask(TASK1);
        // When
        verify(repository).save(anyTask.capture());
        // Then
        assertThat(anyTask.getValue().getId()).isNull();
    }

    @Test(expected = TaskNotFoundException.class)
    public void whenUpdatingTaskItShouldUseTheGivenID() throws TaskNotFoundException {
        // Given & When
        taskService.updateTaskWithId(TASK1_ID, NEW_TASK);
        // Then
        verify(repository).save(anyTask.capture());
        assertThat(anyTask.getValue().getId()).isEqualTo(TASK1_ID);
    }

    @Test
    public void whenDeletingAnTaskItShouldUseTheRepository() {
        // Given & When
        taskService.deleteTask(TASK1_ID);
        // Then
        verify(repository).deleteById(TASK1_ID);
    }

    @Test
    public void whenDeletingTasksItShouldUseTheRepository() {
        // Given & When
        taskService.deleteAllTask();
        // Then
        verify(repository).deleteAllInBatch();
    }
}