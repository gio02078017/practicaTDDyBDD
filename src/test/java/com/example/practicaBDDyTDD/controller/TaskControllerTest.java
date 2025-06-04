package com.example.practicaBDDyTDD.controller;

import com.example.practicaBDDyTDD.model.Tarea;
import com.example.practicaBDDyTDD.service.TaskService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TaskController.class) // Focuses on testing the web layer for TaskController
class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc; // Used to perform HTTP requests

    @Autowired
    private TaskService mockTaskService;

    @Autowired
    private ObjectMapper objectMapper; //

    private Tarea tarea1;
    private Tarea tarea2;
    private Tarea tarea3;
    private Tarea tarea4;

    @TestConfiguration
    static class TaskServiceTestConfiguration {
        @Bean
        public TaskService taskService() {
            // This is where you create your Mockito mock
            return mock(TaskService.class);
        }
    }

    @BeforeEach
    void setUp() {
        reset(mockTaskService);

        tarea1 = new Tarea(1L, "Comprar víveres", "Leche, pan, huevos, frutas y verduras.");
        tarea2 = new Tarea(2L, "Preparar presentación", "Revisar los correos electrónicos pendientes.", true);
        tarea3 = new Tarea();
        tarea3.setId(3L);
        tarea3.setTitulo("Pagar facturas");

        tarea4 = new Tarea();
        tarea4.setId(4L);
        tarea4.setDescripcion("Factura de luz y agua.");

    }

    @Test
    void shouldCreateTaskSuccessfullyWithValidData() throws Exception {// As per Gherkin, should be false by default

        when(mockTaskService.createTask(any(Tarea.class))).thenReturn(tarea1);

        mockMvc.perform(post("/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(tarea1)))
                .andExpect(jsonPath("$.id").value(tarea1.getId()))
                .andExpect(status().isCreated())
                .andExpect(status().is2xxSuccessful());

        verify(mockTaskService).createTask(any(Tarea.class));// Verify the specific task was created
        verifyNoMoreInteractions(mockTaskService); // Ensure no other interactions with the mock
    }

    @Test
    void shouldCreateTaskSuccessfullyWithValidDataConEstadoCompletado() throws Exception {// As per Gherkin, should be false by default

        when(mockTaskService.createTask(any(Tarea.class))).thenReturn(tarea2);

        mockMvc.perform(post("/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(tarea2)))
                .andExpect(jsonPath("$.id").value(tarea2.getId()))
                .andExpect(status().isCreated())
                .andExpect(status().is2xxSuccessful());

        verify(mockTaskService).createTask(any(Tarea.class));// Verify the specific task was created
        verifyNoMoreInteractions(mockTaskService);
    }

    @Test
    void shouldCreateTaskSuccessfullyWithoutValidDataDescripcionNull() throws Exception {// As per Gherkin, should be false by default

        when(mockTaskService.createTask(any(Tarea.class))).thenReturn(tarea3);

        // Then: Perform POST request and assert the response
        mockMvc.perform(post("/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(tarea3)))
                .andExpect(status().isBadRequest())
                .andExpect(status().is4xxClientError());

        verifyNoMoreInteractions(mockTaskService);
    }

    @Test
    void shouldCreateTaskSuccessfullyWithoutValidDataTituloNull() throws Exception {// As per Gherkin, should be false by default

        when(mockTaskService.createTask(any(Tarea.class))).thenReturn(tarea3);

        // Then: Perform POST request and assert the response
        mockMvc.perform(post("/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(tarea4)))
                .andExpect(status().isBadRequest())
                .andExpect(status().is4xxClientError());

        verifyNoMoreInteractions(mockTaskService);
    }
}