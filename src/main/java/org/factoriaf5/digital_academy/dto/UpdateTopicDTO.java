package org.factoriaf5.digital_academy.dto;

public class UpdateTopicDTO {
    private String topic;

    public UpdateTopicDTO() {}

    public UpdateTopicDTO(String topic) {
        this.topic = topic;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }
}
