package org.factoriaf5.digital_academy.global;

import jakarta.servlet.http.HttpServletRequest;
import org.factoriaf5.digital_academy.dto.ExceptionResponseDTO;
import org.factoriaf5.digital_academy.exception.InvalidStatusException;
import org.factoriaf5.digital_academy.exception.RequestNotFoundException;
import org.factoriaf5.digital_academy.exception.TopicNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RequestNotFoundException.class)
    public ResponseEntity<ExceptionResponseDTO> handleRequestNotFound(
            RequestNotFoundException ex,
            HttpServletRequest request
    ) {
        ExceptionResponseDTO response = new ExceptionResponseDTO(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                "Not Found",
                ex.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(TopicNotFoundException.class)
    public ResponseEntity<ExceptionResponseDTO> handleTopicNotFound(
            TopicNotFoundException ex,
            HttpServletRequest request
    ) {
        ExceptionResponseDTO response = new ExceptionResponseDTO(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                "Bad Request",
                ex.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(InvalidStatusException.class)
    public ResponseEntity<ExceptionResponseDTO> handleInvalidStatus(
            InvalidStatusException ex,
            HttpServletRequest request
    ) {
        ExceptionResponseDTO response = new ExceptionResponseDTO(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                "Bad Request",
                ex.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponseDTO> handleGenericException(
            Exception ex,
            HttpServletRequest request
    ) {
        ExceptionResponseDTO response = new ExceptionResponseDTO(
                LocalDateTime.now(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Internal Server Error",
                ex.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}
