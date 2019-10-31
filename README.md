# ToDo App RESTful API

RESTful API para gestionar tareas por realizar (ToDo). 

Proyecto desarrollado en el marco de una prueba técnica. 

### Pre-requisitos
- Tener instalado Eclipse o Spring Tool Suite (STS) 4 con 
- Tener instalado PostgreSQL > 9.4

### Instalación

- Descargar el zip (y descomprimir) o clonar el repositorio
- Abrir una consola de comandos y dirigirse al directorio raíz del repositorio (el que contiene el archivo pom.xml)
- Ejecutar 
```
psql -h <host> -d <database> -U <user_name> -p <port> -a -w -f ./database-sql/todoapp.sql
```
- Abrir Eclipse/STS
  - File->Import->Existing Maven Projects -> seleccionar directorio raíz del repositorio
  - Seleccionar proyecto todoapp
- Esperar que importe las dependencias. Recomendable hacer Project->Clean all y Project->Build all
- Asegurar que el archivo application.properties en la raíz del proyecto está correctamente configurado de acuerdo a usuario, contraseña, host y puerto de la base de datos.
- En Project Explorer, click derecho sobre el proyecto todoapp
  - Run as Spring Boot App
- El proyecto corre por defecto en http://localhost:8080/


### Endpoints

GET /api/v1/tasks --> obtener listado de tareas. Admite query parameters id, descripcion y status
GET /api/v1/tasks/:id --> obtener tarea con id=:id
POST /api/v1/tasks --> guardar una tarea nueva o existente.
PUT /api/v1/tasks/:id/resolved --> marcar tarea con id=:id como resuelta.

### Autor
Emanuel Richieri <emanuel.richieri@gmail.com>
