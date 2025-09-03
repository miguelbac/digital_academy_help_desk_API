package org.factoriaf5.digital_academy.exception;

public class RequestNotFoundException extends RuntimeException {
    public RequestNotFoundException(Long id) {
        super("Request with ID " + id + " not found");
    }
}
