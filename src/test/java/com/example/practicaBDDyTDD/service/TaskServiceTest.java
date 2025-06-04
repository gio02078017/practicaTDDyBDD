package com.example.practicaBDDyTDD.service;

import com.example.practicaBDDyTDD.model.Tarea;
import com.example.practicaBDDyTDD.repository.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class) // Enables Mockito annotations for JUnit 5
public class TaskServiceTest {

    @Mock // Creates a mock instance of TaskRepository
    private TaskRepository taskRepository;

    @InjectMocks // Injects the mocked taskRepository into taskService
    private TaskService taskService; // This is the class under test

    private Tarea tarea1;
    private Tarea tarea2;
    private Tarea tarea3;
    private Tarea tarea4;

    @BeforeEach
    void setUp() {
        // Initialize a sample Tarea object before each test
        tarea1 = new Tarea(1L, "Comprar víveres", "Leche, pan, huevos, frutas y verduras.");
        tarea2 = new Tarea(2L, "Preparar presentación", "Revisar los correos electrónicos pendientes.", true);
        tarea3 = new Tarea();
        tarea3.setId(3L);
        tarea3.setTitulo("Pagar facturas");

        tarea4 = new Tarea();
        tarea4.setId(4L);
        tarea4.setDescripcion("Factura de luz y agua.");// Simulate an ID that would be assigned after saving
    }

    @Test
    void createTask_shouldSaveAndReturnTask() {
        // Given: Define the behavior of the mocked TaskRepository
        // When taskRepository.save() is called with any Tarea object,
        // it should return our pre-defined sampleTarea.
        when(taskRepository.save(any(Tarea.class))).thenReturn(tarea1);

        // When: Call the method under test in TaskService
        Tarea createdTarea = taskService.createTask(tarea1);

        // Then: Assertions to verify the outcome
        assertNotNull(createdTarea, "The created task should not be null");
        assertEquals(tarea1.getId(), createdTarea.getId(), "The ID of the created task should match the expected ID");
        assertEquals(tarea1.getDescripcion(), createdTarea.getDescripcion(), "The description should match");
        assertEquals(tarea1.isCompleted(), createdTarea.isCompleted(), "The completion status should match");

        // Verify: Ensure that taskRepository.save() was called exactly once
        // with any Tarea object.
        verify(taskRepository, times(1)).save(any(Tarea.class));

        // Verify that no other interactions occurred with the mock
        verifyNoMoreInteractions(taskRepository);
    }

    @Test
    void createTask_shouldSaveAndReturnTask2() {
        // Given: Define the behavior of the mocked TaskRepository
        // When taskRepository.save() is called with any Tarea object,
        // it should return our pre-defined sampleTarea.
        when(taskRepository.save(any(Tarea.class))).thenReturn(tarea2);

        // When: Call the method under test in TaskService
        Tarea createdTarea = taskService.createTask(tarea2);

        // Then: Assertions to verify the outcome
        assertNotNull(createdTarea, "The created task should not be null");
        assertEquals(tarea2.getId(), createdTarea.getId(), "The ID of the created task should match the expected ID");
        assertEquals(tarea2.getDescripcion(), createdTarea.getDescripcion(), "The description should match");
        assertEquals(tarea2.isCompleted(), createdTarea.isCompleted(), "The completion status should match");

        // Verify: Ensure that taskRepository.save() was called exactly once
        // with any Tarea object.
        verify(taskRepository, times(1)).save(any(Tarea.class));

        // Verify that no other interactions occurred with the mock
        verifyNoMoreInteractions(taskRepository);
    }
}