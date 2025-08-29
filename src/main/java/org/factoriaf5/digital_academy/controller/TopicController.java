package org.factoriaf5.digital_academy.controller;

import org.factoriaf5.digital_academy.contract.TopicServiceContract;
import org.factoriaf5.digital_academy.dto.TopicDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/topics")
public class TopicController {

    private final TopicServiceContract service;

    public TopicController(TopicServiceContract service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<TopicDTO>> getAll() {
        return ResponseEntity.ok(service.getAllTopics());
    }
}
