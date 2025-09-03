package org.factoriaf5.digital_academy.exception;

public class InvalidStatusException extends RuntimeException {
    public InvalidStatusException(String status) {
        super("Invalid status: " + status + ". Allowed values: pending, in_progress, attended");
    }
}
