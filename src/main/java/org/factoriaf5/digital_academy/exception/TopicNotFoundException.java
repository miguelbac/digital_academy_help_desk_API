package org.factoriaf5.digital_academy.exception;

public class TopicNotFoundException extends RuntimeException {
    
    public TopicNotFoundException(String topicName) {
        super("Topic '" + topicName + "' not found");
    }
    
    public TopicNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}