package org.factoriaf5.digital_academy.controller;

import org.factoriaf5.digital_academy.dto.*;
import org.factoriaf5.digital_academy.service.RequestService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/requests")
public class RequestController {

    private final RequestService service;

    public RequestController(RequestService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<RequestResponseDTO> createRequest(@RequestBody RequestCreateDTO dto) {
        return ResponseEntity.ok(service.createRequest(dto));
    }

    @GetMapping
    public ResponseEntity<List<RequestResponseDTO>> getAllRequests() {
        return ResponseEntity.ok(service.getAllRequests());
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<RequestResponseDTO> updateStatus(
            @PathVariable Long id,
            @RequestBody UpdateStatusDTO dto) {
        return ResponseEntity.ok(service.updateStatus(id, dto.getStatus(), dto.getTechnician()));
    }

    @PatchMapping("/{id}/description")
    public ResponseEntity<RequestResponseDTO> updateDescription(
            @PathVariable Long id,
            @RequestBody UpdateDescriptionDTO dto) {
        return ResponseEntity.ok(service.updateDescription(id, dto.getDescription()));
    }

    @PatchMapping("/{id}/topic")
    public ResponseEntity<RequestResponseDTO> updateTopic(
            @PathVariable Long id,
            @RequestBody UpdateTopicDTO dto) {
        return ResponseEntity.ok(service.updateTopic(id, dto.getTopic()));
    }

    @PatchMapping("/{id}/requester")
    public ResponseEntity<RequestResponseDTO> reassignRequester(
            @PathVariable Long id,
            @RequestBody ReassignRequesterDTO dto) {
        return ResponseEntity.ok(service.reassignRequester(id, dto.getRequesterName()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRequest(@PathVariable Long id) {
        service.deleteRequest(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/topic/{topic}")
    public ResponseEntity<List<RequestResponseDTO>> getRequestsByTopic(@PathVariable String topic) {
        return ResponseEntity.ok(service.getRequestsByTopic(topic));
    }

    @GetMapping("/search")
    public ResponseEntity<List<RequestResponseDTO>> searchByRequesterName(@RequestParam String name) {
        return ResponseEntity.ok(service.searchByRequesterName(name));
    }
}
