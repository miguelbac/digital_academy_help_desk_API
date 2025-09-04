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
git clone https://github.com/miguelbac/digital_academy_help_desk_API.git
cd digital_academy_help_desk_API
```
Construir con Maven:
```
mvn clean install
```

Ejecutar:
```
mvn spring-boot:run
```

La API corre en: http://localhost:8080

📄 Swagger UI

Documentación y pruebas interactivas:

http://localhost:8080/swagger-ui.html

🗂️ Base de datos H2

URL: jdbc:h2:mem:testdb

Usuario: SA

Contraseña: (vacío)

Consola: http://localhost:8080/h2-console

📝 Diagrama de clases
```
classDiagram
    %% ===========================
    %% Controllers
    %% ===========================
    class RequestController {
        - RequestServiceContract service
        + create(dto: RequestCreateDTO) ResponseEntity<RequestResponseDTO>
        + getAll() ResponseEntity<List<RequestResponseDTO>>
        + updateTopic(id: Long, dto: UpdateTopicDTO) ResponseEntity<RequestResponseDTO>
        + updateDescription(id: Long, dto: UpdateDescriptionDTO) ResponseEntity<RequestResponseDTO>
        + updateStatus(id: Long, dto: UpdateStatusDTO) ResponseEntity<RequestResponseDTO>
    }

    class TopicController {
        - TopicServiceContract service
        + getAll() ResponseEntity<List<TopicDTO>>
    }

    %% ===========================
    %% Contracts
    %% ===========================
    class RequestServiceContract {
        <<interface>>
        + createRequest(dto: RequestCreateDTO) RequestResponseDTO
        + getAllRequests() List<RequestResponseDTO>
        + updateTopic(id: Long, topic: String) RequestResponseDTO
        + updateDescription(id: Long, description: String) RequestResponseDTO
        + updateStatus(id: Long, status: String) RequestResponseDTO
    }

    class TopicServiceContract {
        <<interface>>
        + getAllTopics() List<TopicDTO>
    }

    %% ===========================
    %% DTOs
    %% ===========================
    class RequestCreateDTO {
        + requesterName: String
        + description: String
        + topic: String
    }

    class RequestResponseDTO {
        + id: Long
        + requesterName: String
        + description: String
        + topic: String
        + status: String
        + technician: String
        + createdAt: Date
        + updatedAt: Date
        + attendedAt: Date
    }

    class UpdateTopicDTO {
        + topic: String
    }

    class UpdateDescriptionDTO {
        + description: String
    }

    class UpdateStatusDTO {
        + status: String
    }

    class TopicDTO {
        + id: Long
        + name: String
    }

    %% ===========================
    %% Relations
    %% ===========================
    RequestController --> RequestServiceContract
    TopicController --> TopicServiceContract

    RequestController --> RequestCreateDTO
    RequestController --> RequestResponseDTO
    RequestController --> UpdateTopicDTO
    RequestController --> UpdateDescriptionDTO
    RequestController --> UpdateStatusDTO

    TopicController --> TopicDTO
```

🧪 Captura de tests

<img width="358" height="222" alt="image" src="https://github.com/user-attachments/assets/40a45b3e-6e08-4069-8714-3f3120bde899" />
