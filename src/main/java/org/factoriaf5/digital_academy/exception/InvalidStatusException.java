package org.factoriaf5.digital_academy.exception;

public class InvalidStatusException extends RuntimeException {
    
    public InvalidStatusException(String status) {
        super("Invalid status: '" + status + "'. Valid statuses are: pending, in_progress, attended");
    }
    
    public InvalidStatusException(String message, Throwable cause) {
        super(message, cause);
    }
}