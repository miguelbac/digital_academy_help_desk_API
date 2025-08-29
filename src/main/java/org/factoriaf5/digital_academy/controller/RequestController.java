package org.factoriaf5.digital_academy.controller;

import org.factoriaf5.digital_academy.contract.RequestServiceContract;
import org.factoriaf5.digital_academy.dto.RequestCreateDTO;
import org.factoriaf5.digital_academy.dto.RequestResponseDTO;
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
        RequestResponseDTO response = service.createRequest(dto);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<RequestResponseDTO>> getAll() {
        List<RequestResponseDTO> requests = service.getAllRequests();
        return ResponseEntity.ok(requests);
    }
}
