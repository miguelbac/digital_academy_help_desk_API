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

## 🚀 Funcionalidades principales

### Requests

| Método | Endpoint | Descripción | Body | Respuesta |
|--------|---------|-------------|------|-----------|
| `GET` | `/api/v1/requests` | Listar todas las solicitudes | - | Lista de `RequestResponseDTO` |
| `POST` | `/api/v1/requests` | Crear una nueva solicitud | `RequestCreateDTO` | `RequestResponseDTO` creado |
| `PATCH` | `/api/v1/requests/{id}/status` | Actualizar estado de una solicitud | `UpdateStatusDTO` | `RequestResponseDTO` actualizado |
| `PATCH` | `/api/v1/requests/{id}/description` | Actualizar descripción de una solicitud | `UpdateDescriptionDTO` | `RequestResponseDTO` actualizado |
| `PATCH` | `/api/v1/requests/{id}/topic` | Cambiar topic de la solicitud | `UpdateTopicDTO` | `RequestResponseDTO` actualizado |
| `PATCH` | `/api/v1/requests/{id}/requester` | Cambiar solicitante de la solicitud | `{ "requesterName": "NuevoNombre" }` | `RequestResponseDTO` actualizado |
| `GET` | `/api/v1/requests/search?requesterName=Alice` | Buscar solicitudes por nombre del solicitante | - | Lista de `RequestResponseDTO` |
| `GET` | `/api/v1/requests/topic/{topicName}` | Obtener solicitudes por topic | - | Lista de `RequestResponseDTO` |
| `DELETE` | `/api/v1/requests/{id}` | Eliminar solicitud | - | 204 No Content |

---

### Topics

| Método | Endpoint | Descripción | Body | Respuesta |
|--------|---------|-------------|------|-----------|
| `GET` | `/api/v1/topics` | Listar todos los topics disponibles | - | Lista de `TopicDTO` |

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

```mermaid
classDiagram
    %% =======================
    %% MODELS
    %% =======================
    class Request {
        +Long id
        +String requesterName
        +String topic
        +String description
        +String status
        +String technician
        +LocalDateTime createdAt
        +LocalDateTime attendedAt
        +LocalDateTime updatedAt
    }

    class Topic {
        +Long id
        +String name
    }

    %% =======================
    %% DTOs
    %% =======================
    class RequestCreateDTO {
        +String requesterName
        +String topic
        +String description
    }

    class RequestResponseDTO {
        +Long id
        +String requesterName
        +String topic
        +String description
        +String status
        +String attendedBy
        +LocalDateTime createdAt
        +LocalDateTime attendedAt
    }

    class UpdateDescriptionDTO {
        +String description
    }

    class UpdateStatusDTO {
        +String status
    }

    class UpdateTopicDTO {
        +String topic
    }

    class TopicDTO {
        +Long id
        +String name
    }

    %% =======================
    %% MAPPERS
    %% =======================
    class RequestMapper {
        +toDTO(request: Request): RequestResponseDTO
    }

    %% =======================
    %% SERVICES / CONTRACTS
    %% =======================
    class RequestService {
        +createRequest(dto: RequestCreateDTO): RequestResponseDTO
        +getAllRequests(): List~RequestResponseDTO~
        +updateStatus(id: Long, status: String): RequestResponseDTO
        +updateDescription(id: Long, desc: String): RequestResponseDTO
        +updateTopic(id: Long, topic: String): RequestResponseDTO
        +reassignRequester(id: Long, requester: String): RequestResponseDTO
        +deleteRequest(id: Long)
        +getRequestsByTopic(topic: String): List~RequestResponseDTO~
        +searchByRequesterName(name: String): List~RequestResponseDTO~
    }

    class TopicService {
        +getAllTopics(): List~TopicDTO~
    }

    %% =======================
    %% REPOSITORIES
    %% =======================
    class RequestRepository {
        +findAllByOrderByCreatedAtAsc(): List~Request~
        +findByRequesterNameContainingIgnoreCaseOrderByCreatedAtAsc(name: String): List~Request~
        +findByTopicOrderByCreatedAtAsc(topic: String): List~Request~
    }

    class TopicRepository {
        +existsByName(name: String): boolean
    }

    %% =======================
    %% RELATIONSHIPS
    %% =======================
    RequestService --> RequestRepository
    RequestService --> TopicRepository
    RequestService --> RequestMapper

    TopicService --> TopicRepository

    RequestMapper --> Request
    RequestMapper --> RequestResponseDTO
```

🧪 Captura de tests

<img width="358" height="222" alt="image" src="https://github.com/user-attachments/assets/40a45b3e-6e08-4069-8714-3f3120bde899" />
