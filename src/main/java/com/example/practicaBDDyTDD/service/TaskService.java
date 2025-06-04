package com.example.practicaBDDyTDD.service;

import com.example.practicaBDDyTDD.model.Tarea;
import com.example.practicaBDDyTDD.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Tarea createTask(Tarea tarea) {
        return taskRepository.save(tarea);
    }
}
