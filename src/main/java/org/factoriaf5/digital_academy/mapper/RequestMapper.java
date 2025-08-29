package org.factoriaf5.digital_academy.mapper;

import org.factoriaf5.digital_academy.dto.RequestResponseDTO;
import org.factoriaf5.digital_academy.model.Request;

public class RequestMapper {

    public static RequestResponseDTO toDTO(Request request) {
        return new RequestResponseDTO(
                request.getId(),
                request.getRequesterName(),
                request.getTopic(),
                request.getDescription(),
                request.getStatus(),
                request.getTechnician(),
                request.getCreatedAt(),
                request.getAttendedAt()
        );
    }
}
