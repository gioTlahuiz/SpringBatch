## Proyecto Spring Batch Academia Xideral

### Para este proyecto se integro la creacion de una API rest con distintos metodos HTTP para la peticion de informacion que posteriormente se persiste en MySQL y MongoDB.

### Se integraron pruebas unitarias para la verificacion de funcionalidades con JUnit y Mockito.

## ¿Que se construyo?

### Se construyo un Job con 2 steps básicos:

### 1.Se tomo estudiantes.csv -> calculamos el promedio -> Mandamos a una tabla en MySQL(estudiantes_procesados).

### 2.De la tabla en MySQL -> determinamos un estado -> se guarda una coleccion en MongoDB(reportes_estudiantes).

| Parte | Qué construyes | Temas que cubre |
|-------|----------------|-----------------|
| **Parte 1** (Pasos 1–13) | El proceso batch (CSV → MySQL → MongoDB) | Spring Batch, Maven, MySQL, MongoDB, Spring Boot, Spring Core |
| **Parte 2** (Pasos 14–21) | Una **API REST** sobre esos datos | Spring MVC, servicios RESTful, Spring Data JPA, verbos HTTP, URIs, códigos de respuesta, HTTP/HTTPS |
| **Parte 3** (Pasos 22–24) | **Pruebas unitarias** | JUnit, assertions, Mockito |
