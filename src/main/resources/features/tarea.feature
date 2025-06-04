Característica: Creación de Tarea en la API
  Como usuario de la API de tareas
  Quiero poder crear una nueva tarea con título, descripción y estado
  Para poder registrar y organizar mis pendientes en una aplicación web o móvil

  Escenario: Creación exitosa de una tarea con título y descripción obligatorios
    Dado que la API de tareas está disponible
    Cuando envío una solicitud POST a "/tasks" con un cuerpo JSON que contiene:
      """
      {
        "titulo": "Comprar víveres para la semana",
        "descripcion": "Leche, pan, huevos, frutas y verduras."
      }
      """
    Entonces la respuesta debe tener un código de estado 201 Created
    Y la respuesta debe ser un objeto JSON que contenga:
      | clave         | tipo   | valor                                      |
      | id            | número | (cualquier ID generado)                    |
      | titulo        | cadena | "Comprar víveres para la semana"           |
      | descripcion   | cadena | "Leche, pan, huevos, frutas y verduras."   |
      | completed     | booleano | false                                     |
    Y la tarea debe haber sido guardada en la base de datos en memoria (H2) con el campo 'completed' en 'false'

  Escenario: Intento de creación de tarea sin título
    Dado que la API de tareas está disponible
    Cuando envío una solicitud POST a "/tasks" con un cuerpo JSON que contiene:
      """
      {
        "descripcion": "Revisar los correos electrónicos pendientes."
      }
      """
    Entonces la respuesta debe tener un código de estado 400 Bad Request
    Y la respuesta debe contener un mensaje de error que indique que el título es obligatorio

  Escenario: Intento de creación de tarea sin descripción
    Dado que la API de tareas está disponible
    Cuando envío una solicitud POST a "/tasks" con un cuerpo JSON que contiene:
      """
      {
        "titulo": "Preparar presentación"
      }
      """
    Entonces la respuesta debe tener un código de estado 400 Bad Request
    Y la respuesta debe contener un mensaje de error que indique que la descripción es obligatoria

  Escenario: Creación de tarea con campo 'completed' especificado (debería ser ignorado o sobrescrito a false)
    Dado que la API de tareas está disponible
    Cuando envío una solicitud POST a "/tasks" con un cuerpo JSON que contiene:
      """
      {
        "titulo": "Pagar facturas",
        "descripcion": "Factura de luz y agua.",
        "completed": true
      }
      """
    Entonces la respuesta debe tener un código de estado 201 Created
    Y la respuesta debe ser un objeto JSON donde el campo 'completed' sea 'false'
    Y la tarea debe haber sido guardada en la base de datos en memoria (H2) con el campo 'completed' en 'false'