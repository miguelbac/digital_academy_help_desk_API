package org.factoriaf5.digital_academy.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.factoriaf5.digital_academy.contract.RequestServiceContract;
import org.factoriaf5.digital_academy.dto.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/requests")
@Tag(name = "Requests", description = "Endpoints for managing help desk requests")
public class RequestController {

    private final RequestServiceContract service;

    public RequestController(RequestServiceContract service) {
        this.service = service;
    }

    @Operation(summary = "Create a new request")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Request created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    @PostMapping
    public ResponseEntity<RequestResponseDTO> create(@RequestBody RequestCreateDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createRequest(dto));
    }

    @Operation(summary = "Get all requests")
    @ApiResponse(responseCode = "200", description = "List of all requests")
    @GetMapping
    public ResponseEntity<List<RequestResponseDTO>> getAll() {
        return ResponseEntity.ok(service.getAllRequests());
    }

    @Operation(summary = "Update topic of a request")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Topic updated successfully"),
            @ApiResponse(responseCode = "404", description = "Request or topic not found")
    })
    @PatchMapping("/{id}/topic")
    public ResponseEntity<RequestResponseDTO> updateTopic(
            @PathVariable Long id,
            @RequestBody UpdateTopicDTO dto
    ) {
        return ResponseEntity.ok(service.updateTopic(id, dto.getTopic()));
    }

    @Operation(summary = "Update description of a request")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Description updated successfully"),
            @ApiResponse(responseCode = "404", description = "Request not found")
    })
    @PatchMapping("/{id}/description")
    public ResponseEntity<RequestResponseDTO> updateDescription(
            @PathVariable Long id,
            @RequestBody UpdateDescriptionDTO dto
    ) {
        return ResponseEntity.ok(service.updateDescription(id, dto.getDescription()));
    }

    @Operation(summary = "Update status of a request")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Status updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid status"),
            @ApiResponse(responseCode = "404", description = "Request not found")
    })
    @PatchMapping("/{id}/status")
    public ResponseEntity<RequestResponseDTO> updateStatus(
            @PathVariable Long id,
            @RequestBody UpdateStatusDTO dto
    ) {
        return ResponseEntity.ok(service.updateStatus(id, dto.getStatus()));
    }
}
