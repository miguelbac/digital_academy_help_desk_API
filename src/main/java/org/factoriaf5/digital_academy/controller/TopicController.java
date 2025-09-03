package org.factoriaf5.digital_academy.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.factoriaf5.digital_academy.contract.TopicServiceContract;
import org.factoriaf5.digital_academy.dto.TopicDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/topics")
@Tag(name = "Topics", description = "Endpoints for managing available topics")
public class TopicController {

    private final TopicServiceContract service;

    public TopicController(TopicServiceContract service) {
        this.service = service;
    }

    @Operation(summary = "Get all topics")
    @ApiResponse(responseCode = "200", description = "List of all topics")
    @GetMapping
    public ResponseEntity<List<TopicDTO>> getAll() {
        return ResponseEntity.ok(service.getAllTopics());
    }
}
