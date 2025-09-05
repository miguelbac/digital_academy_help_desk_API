package org.factoriaf5.digital_academy.dto;

import java.time.LocalDateTime;

public class RequestResponseDTO {

    private Long id;
    private String requesterName;
    private String topic;
    private String description;
    private String status; // pending / in_progress / attended
    private String technician; // nuevo campo
    private LocalDateTime createdAt;
    private LocalDateTime attendedAt;

    public RequestResponseDTO(Long id, String requesterName, String topic, String description,
                              String status, String technician,
                              LocalDateTime createdAt, LocalDateTime attendedAt) {
        this.id = id;
        this.requesterName = requesterName;
        this.topic = topic;
        this.description = description;
        this.status = status;
        this.technician = technician;
        this.createdAt = createdAt;
        this.attendedAt = attendedAt;
    }

    // Getters y setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getRequesterName() { return requesterName; }
    public void setRequesterName(String requesterName) { this.requesterName = requesterName; }
    public String getTopic() { return topic; }
    public void setTopic(String topic) { this.topic = topic; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getTechnician() { return technician; }
    public void setTechnician(String technician) { this.technician = technician; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public LocalDateTime getAttendedAt() { return attendedAt; }
    public void setAttendedAt(LocalDateTime attendedAt) { this.attendedAt = attendedAt; }
}
