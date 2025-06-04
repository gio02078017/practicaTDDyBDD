package com.example.practicaBDDyTDD.repository;

import com.example.practicaBDDyTDD.model.Tarea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Tarea, Long> {}
