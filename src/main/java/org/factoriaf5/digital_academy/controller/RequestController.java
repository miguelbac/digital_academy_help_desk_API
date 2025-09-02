package org.factoriaf5.digital_academy.controller;

import org.factoriaf5.digital_academy.contract.RequestServiceContract;
import org.factoriaf5.digital_academy.dto.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/requests")
public class RequestController {

    private final RequestServiceContract service;

    public RequestController(RequestServiceContract service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<RequestResponseDTO> create(@RequestBody RequestCreateDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createRequest(dto));
    }

    @GetMapping
    public ResponseEntity<List<RequestResponseDTO>> getAll() {
        return ResponseEntity.ok(service.getAllRequests());
    }

    @PatchMapping("/{id}/topic")
    public ResponseEntity<RequestResponseDTO> updateTopic(
            @PathVariable Long id,
            @RequestBody UpdateTopicDTO dto
    ) {
        return ResponseEntity.ok(service.updateTopic(id, dto.getTopic()));
    }

    @PatchMapping("/{id}/description")
    public ResponseEntity<RequestResponseDTO> updateDescription(
            @PathVariable Long id,
            @RequestBody UpdateDescriptionDTO dto
    ) {
        return ResponseEntity.ok(service.updateDescription(id, dto.getDescription()));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<RequestResponseDTO> updateStatus(
            @PathVariable Long id,
            @RequestBody UpdateStatusDTO dto
    ) {
        return ResponseEntity.ok(service.updateStatus(id, dto.getStatus()));
    }
}
