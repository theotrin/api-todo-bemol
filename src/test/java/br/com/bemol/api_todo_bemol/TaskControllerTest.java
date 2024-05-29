package br.com.bemol.api_todo_bemol;

import br.com.bemol.api_todo_bemol.controller.TaskController;
import br.com.bemol.api_todo_bemol.model.Task;
import br.com.bemol.api_todo_bemol.repository.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class TaskControllerTest {

    private MockMvc mockMvc;

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskController taskController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(taskController).build();
    }

    @Test
    void getAllTasks() throws Exception {
        Task task1 = new Task();
        task1.setId(1L);
        task1.setDescription("Task 1");
        task1.setCompleted(false);

        Task task2 = new Task();
        task2.setId(2L);
        task2.setDescription("Task 2");
        task2.setCompleted(false);

        when(taskRepository.findAll()).thenReturn(Arrays.asList(task1, task2));

        mockMvc.perform(get("/api/tasks"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].description").value("Task 1"))
                .andExpect(jsonPath("$[0].completed").value(false))
                .andExpect(jsonPath("$[1].id").value(2L))
                .andExpect(jsonPath("$[1].description").value("Task 2"))
                .andExpect(jsonPath("$[1].completed").value(false));
    }

    @Test
    void createTask() throws Exception {
        Task task = new Task();
        task.setDescription("Theo na Bemol");

        Task savedTask = new Task();
        savedTask.setId(1L);
        savedTask.setDescription("Theo na Bemol");
        savedTask.setCompleted(false);

        when(taskRepository.findLastTaskId()).thenReturn(0L);
        when(taskRepository.save(any(Task.class))).thenReturn(savedTask);

        mockMvc.perform(post("/api/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"description\": \"Theo na Bemol\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.description").value("Theo na Bemol"))
                .andExpect(jsonPath("$.completed").value(false));
    }

    @Test
    void deleteTask() throws Exception {
        Task task = new Task();
        task.setId(1L);
        task.setDescription("Task to be deleted");
        task.setCompleted(false);

        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));

        mockMvc.perform(delete("/api/tasks/1"))
                .andExpect(status().isOk());

        verify(taskRepository, times(1)).delete(task);
    }

    @Test
    void deleteTaskNotFound() throws Exception {
        when(taskRepository.findById(1L)).thenReturn(Optional.empty());

        mockMvc.perform(delete("/api/tasks/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    void completeTask() throws Exception {
        Task task = new Task();
        task.setId(1L);
        task.setDescription("Task to be completed");
        task.setCompleted(false);

        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));
        when(taskRepository.save(any(Task.class))).thenReturn(task);

        mockMvc.perform(put("/api/tasks/1/complete"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.description").value("Task to be completed"))
                .andExpect(jsonPath("$.completed").value(true));
    }

    @Test
    void completeTaskNotFound() throws Exception {
        when(taskRepository.findById(1L)).thenReturn(Optional.empty());

        mockMvc.perform(put("/api/tasks/1/complete"))
                .andExpect(status().isNotFound());
    }
}
