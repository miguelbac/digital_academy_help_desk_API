package org.factoriaf5.digital_academy.exception;

public class TopicNotFoundException extends RuntimeException {
    public TopicNotFoundException(String topic) {
        super("Topic not found: " + topic);
    }
}
