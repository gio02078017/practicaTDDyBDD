# Task Management API with BDD and TDD

## Description
This project implements a REST API for task management using **Spring Boot**. It focuses on practicing **Behavior-Driven Development (BDD)** and **Test-Driven Development (TDD)**, integrating tools like **Cucumber** for Gherkin-based tests and **JUnit** for unit and integration testing.

## Features
- **Create tasks** with title, description, and status.
- Validation of required fields (`title` and `description`).
- Data persistence in an in-memory database (H2).
- Automated testing with **Cucumber** and **JUnit**.

## Evidencias

### Las tareas deben guardarse con un campo booleano completed por defecto en false.
![img.png](img.png)

### Las tareas deben guardarse con un campo booleano completed e ntrue.|
![img_1.png](img_1.png)

### Las tareas sin titulo
![img_2.png](img_2.png)

### Las tareas con titulo vacio
![img_7.png](img_7.png)

### Las tareas sin descripción
![img_3.png](img_3.png)

### Las tareas con descripción vacía
![img_8.png](img_8.png)

## Evidencias TaskControllerTest
![img_5.png](img_5.png)

## Evidencias TaskServiceTest
![img_6.png](img_6.png)




