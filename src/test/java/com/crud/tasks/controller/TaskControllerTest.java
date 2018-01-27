package com.crud.tasks.controller;

import com.crud.tasks.builders.TaskBuilder;
import com.crud.tasks.builders.TaskDtoBuilder;
import com.crud.tasks.domain.*;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.TaskService;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TaskController.class)
public class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TaskService taskService;

    @MockBean
    private TaskMapper taskMapper;

    private static final Long TASK_DTO1_ID = 1L;
    private static final TaskDto TASK_DTO1 = new TaskDtoBuilder()
            .id(TASK_DTO1_ID)
            .title("Test1")
            .content("Test1")
            .build();

    private static final TaskDto TASK_DTO2 = new TaskDtoBuilder()
            .id(2L)
            .title("Test2")
            .content("Test2")
            .build();

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
            .title("Test3")
            .content("Test3")
            .build();


    @Test
    public void shouldGetEmptyTasksList() throws Exception {
        // Given
        List<Task> tasks = new ArrayList<>();
        List<TaskDto> taskDtos = new ArrayList<>();

        given(taskService.getAllTasks()).willReturn(tasks);
        given(taskMapper.mapToTaskDtoList(tasks)).willReturn(taskDtos);

        // When & Then
        mockMvc.perform(get("/v1/tasks")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    public void shouldGetAllTasksFromTasksLists() throws Exception {

        // Given
        given(taskService.getAllTasks()).willReturn(Arrays.asList(TASK1, TASK2));
        given(taskMapper.mapToTaskDtoList(Arrays.asList(TASK1, TASK2))).willReturn(Arrays.asList(TASK_DTO1, TASK_DTO2));

        // When & Then
        mockMvc.perform(get("/v1/tasks")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].title", is("Test1")))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].title", is("Test2")));
    }

    @Test
    public void shouldGetTaskWithPathIdFromTaskList() throws Exception {

        //Given
        given(taskService.getTask(TASK1_ID)).willReturn(Optional.of(TASK1));
        given(taskMapper.mapToTaskDto(TASK1)).willReturn(TASK_DTO1);

        //When & Then
        mockMvc.perform(get("/v1/tasks/{id}", TASK1_ID)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is("Test1")));

    }

    @Test
    public void shouldNotGetTaskWithBadPathIdFromTaskList() throws Exception {

        //Given
        given(taskService.getTask(TASK1_ID)).willReturn(Optional.of(TASK1));
        given(taskMapper.mapToTaskDto(TASK1)).willReturn(TASK_DTO1);

        //When & Then
        mockMvc.perform(get("/v1/tasks/{id}", 2L)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());


    }

    @Test
    public void shouldDeleteTaskWithGivenId() throws Exception {
        //Given & When & Then
        mockMvc.perform(delete("/v1/tasks/{id}", TASK1_ID))
                .andExpect(status().isOk());
        verify(taskService, times(1)).deleteTask(TASK1_ID);
    }

    @Test
    public void shouldDeleteAllTasks() throws Exception {
        //Given & When & Then
        mockMvc.perform(delete("/v1/tasks"))
                .andExpect(status().isOk());
        verify(taskService, times(1)).deleteAllTask();
    }

    @Test
    public void shouldUpdateTaskWithGivenId() throws Exception {
        //Given
        given(taskMapper.mapToTask(TASK_DTO1)).willReturn(TASK1);
        given(taskService.updateTaskWithId(TASK1_ID, TASK1)).willReturn(TASK1);
        given(taskMapper.mapToTaskDto(TASK1)).willReturn(TASK_DTO1);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(TASK_DTO1);
        // When & Then
        mockMvc.perform(put("/v1/tasks/{id}", TASK1_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is("Test1")));
    }


    @Test
    public void shouldNotUpdateTaskWhenTaskDoesntExist() throws Exception {
        //Given
        given(taskMapper.mapToTask(TASK_DTO1)).willReturn(TASK1);
        given(taskService.updateTaskWithId(TASK1_ID, TASK1)).willThrow(TaskNotFoundException.class);
        given(taskMapper.mapToTaskDto(TASK1)).willReturn(TASK_DTO1);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(TASK_DTO1);
        //When & Then
        mockMvc.perform(put("/v1/tasks/{id}", TASK1_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldPostTaskWithoutId() throws Exception {
        //Given
        Gson gson = new Gson();
        String jsonContent = gson.toJson(NEW_TASK);
        //When & Then
        mockMvc.perform(post("/v1/tasks/")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isCreated());
    }

    @Test
    public void shouldPostTaskWithId() throws Exception {
        //Given
        Gson gson = new Gson();
        String jsonContent = gson.toJson(TASK1);
        //When & Then
        mockMvc.perform(post("/v1/tasks/")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isCreated());
    }
}
