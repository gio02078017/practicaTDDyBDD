package com.example.practicaBDDyTDD.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Tarea {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // ID auto-generado
    private Long id;

    @NotBlank(message = "(título no nulo o vacio).")
    private String titulo;

    @NotBlank(message = "La descripción no nulo o vacio")
    private String descripcion;

    private boolean completed = false;

    public Tarea() {
    }

    public Tarea(Long id, String titulo, String descripcion){
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
    }

    public Tarea(Long id, String titulo, String descripcion, boolean completed) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.completed = completed;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotBlank(message = "El título no puede estar vacío") String getTitulo() {
        return titulo;
    }

    public void setTitulo(@NotBlank(message = "El título no puede estar vacío") String titulo) {
        this.titulo = titulo;
    }

    public @NotBlank(message = "La descripción no puede estar vacía") String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(@NotBlank(message = "La descripción no puede estar vacía") String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
