package org.factoriaf5.digital_academy.dto;

public class RequestCreateDTO {

    private String requesterName;
    private String topic;
    private String description;

    // 🔹 Constructor vacío (necesario para deserialización en Spring y tests)
    public RequestCreateDTO() {}

    // 🔹 Constructor con todos los parámetros
    public RequestCreateDTO(String requesterName, String topic, String description) {
        this.requesterName = requesterName;
        this.topic = topic;
        this.description = description;
    }

    // Getters y setters
    public String getRequesterName() {
        return requesterName;
    }

    public void setRequesterName(String requesterName) {
        this.requesterName = requesterName;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
