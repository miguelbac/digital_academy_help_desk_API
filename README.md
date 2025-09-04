# 🖥️ Digital Academy Help Desk API

API REST para la gestión de **solicitudes (requests)** y **temas (topics)** de soporte, desarrollada con **Spring Boot 3**, **JPA/Hibernate** y **H2 in-memory**. Ideal para fines académicos.

---

## 🛠️ Tecnologías

- Java 21  
- Spring Boot 3  
- Spring Data JPA  
- H2 Database (in-memory)  
- JUnit 5 + Mockito  
- Swagger/OpenAPI  

---

## 📂 Estructura

- **model**: Entidades JPA (`Request`, `Topic`)  
- **dto**: Data Transfer Objects  
- **repository**: Repositorios JPA  
- **service**: Lógica de negocio  
- **mapper**: Conversión entre entidades y DTOs  
- **controller**: Endpoints REST  
- **exception**: Excepciones personalizadas  

---

## 🚀 Funcionalidades principales

### Requests
- Crear, obtener, actualizar y eliminar solicitudes  
- Actualizar estado, descripción, topic o solicitante  
- Buscar por topic o por nombre del solicitante  

### Topics
- Listar todos los topics disponibles  

---

## 🏃‍♂️ Cómo lanzar el proyecto

1. Clonar el repositorio:

```bash
git clone <URL_DEL_REPOSITORIO>
cd digital_academy_help_desk_API
```
Construir con Maven:

mvn clean install


Ejecutar:

mvn spring-boot:run


La API corre en: http://localhost:8080

📄 Swagger UI

Documentación y pruebas interactivas:

http://localhost:8080/swagger-ui.html

🧪 Testing

Tests unitarios y de integración para controllers, services y repositories.

Se ejecutan con H2 in-memory.

mvn test


Cobertura aproximada: 70%

🗂️ Base de datos H2

URL: jdbc:h2:mem:testdb

Usuario: SA

Contraseña: (vacío)

Consola: http://localhost:8080/h2-console

📝 Diagrama de clases

(Aquí se puede insertar el diagrama de clases en Mermaid o imagen)

🖼️ Captura de tests

(Aquí se pueden colocar capturas de los resultados de los tests)
