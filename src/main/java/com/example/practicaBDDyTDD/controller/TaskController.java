package com.example.practicaBDDyTDD.controller;

import com.example.practicaBDDyTDD.model.Tarea;
import com.example.practicaBDDyTDD.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping // Maneja las solicitudes POST a /tasks
    public ResponseEntity<Tarea> createTask(@Valid @RequestBody Tarea taskRequest) {
        Tarea createdTask = taskService.createTask(taskRequest);
        return new ResponseEntity<>(createdTask, HttpStatus.CREATED);
    }
}
