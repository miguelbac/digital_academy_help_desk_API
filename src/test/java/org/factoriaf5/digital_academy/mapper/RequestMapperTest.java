package org.factoriaf5.digital_academy.mapper;

import org.factoriaf5.digital_academy.dto.RequestResponseDTO;
import org.factoriaf5.digital_academy.model.Request;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RequestMapperTest {

    @Test
    void toDTO_shouldMapAllFieldsCorrectly() {
        // Arrange
        LocalDateTime createdAt = LocalDateTime.now();
        LocalDateTime attendedAt = createdAt.plusDays(1);

        Request request = new Request();
        request.setId(1L);
        request.setRequesterName("Alice");
        request.setTopic("Software");
        request.setDescription("App not working");
        request.setStatus("pending");
        request.setTechnician("Bob");
        request.setCreatedAt(createdAt);
        request.setAttendedAt(attendedAt);

        // Act
        RequestResponseDTO dto = RequestMapper.toDTO(request);

        // Assert
        assertEquals(request.getId(), dto.getId());
        assertEquals(request.getRequesterName(), dto.getRequesterName());
        assertEquals(request.getTopic(), dto.getTopic());
        assertEquals(request.getDescription(), dto.getDescription());
        assertEquals(request.getStatus(), dto.getStatus());
        assertEquals(request.getTechnician(), dto.getTechnician());
        assertEquals(request.getCreatedAt(), dto.getCreatedAt());
        assertEquals(request.getAttendedAt(), dto.getAttendedAt());
    }
}
