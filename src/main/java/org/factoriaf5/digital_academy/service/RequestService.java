package org.factoriaf5.digital_academy.service;

import org.factoriaf5.digital_academy.dto.*;
import org.factoriaf5.digital_academy.exception.InvalidStatusException;
import org.factoriaf5.digital_academy.exception.RequestNotFoundException;
import org.factoriaf5.digital_academy.exception.TopicNotFoundException;
import org.factoriaf5.digital_academy.mapper.RequestMapper;
import org.factoriaf5.digital_academy.model.Request;
import org.factoriaf5.digital_academy.repository.RequestRepository;
import org.factoriaf5.digital_academy.repository.TopicRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RequestService {

    private final RequestRepository requestRepo;
    private final TopicRepository topicRepo;

    public RequestService(RequestRepository requestRepo, TopicRepository topicRepo) {
        this.requestRepo = requestRepo;
        this.topicRepo = topicRepo;
    }

    public RequestResponseDTO createRequest(RequestCreateDTO dto) {
        if (!topicRepo.existsByName(dto.getTopic())) {
            throw new TopicNotFoundException(dto.getTopic());
        }

        Request request = new Request();
        request.setRequesterName(dto.getRequesterName());
        request.setTopic(dto.getTopic());
        request.setDescription(dto.getDescription());
        request.setStatus("pending");
        request.setCreatedAt(LocalDateTime.now());
        request.setUpdatedAt(LocalDateTime.now());

        return RequestMapper.toDTO(requestRepo.save(request));
    }

    public List<RequestResponseDTO> getAllRequests() {
        return requestRepo.findAllByOrderByCreatedAtAsc()
                .stream()
                .map(RequestMapper::toDTO)
                .toList();
    }

    public RequestResponseDTO updateStatus(Long id, String status, String technician) {
        Request request = requestRepo.findById(id)
                .orElseThrow(() -> new RequestNotFoundException(id));

        if (!status.equals("pending") && !status.equals("in_progress") && !status.equals("attended")) {
            throw new InvalidStatusException(status);
        }

        request.setStatus(status);
        request.setUpdatedAt(LocalDateTime.now());

        if ("attended".equals(status)) {
            request.setTechnician(technician);
            request.setAttendedAt(LocalDateTime.now());
        }

        return RequestMapper.toDTO(requestRepo.save(request));
    }

    public RequestResponseDTO updateDescription(Long id, String desc) {
        Request request = requestRepo.findById(id)
                .orElseThrow(() -> new RequestNotFoundException(id));

        request.setDescription(desc);
        request.setUpdatedAt(LocalDateTime.now());

        return RequestMapper.toDTO(requestRepo.save(request));
    }

    public RequestResponseDTO updateTopic(Long id, String topic) {
        if (!topicRepo.existsByName(topic)) {
            throw new TopicNotFoundException(topic);
        }

        Request request = requestRepo.findById(id)
                .orElseThrow(() -> new RequestNotFoundException(id));

        request.setTopic(topic);
        request.setUpdatedAt(LocalDateTime.now());

        return RequestMapper.toDTO(requestRepo.save(request));
    }

    public RequestResponseDTO reassignRequester(Long id, String requester) {
        Request request = requestRepo.findById(id)
                .orElseThrow(() -> new RequestNotFoundException(id));

        request.setRequesterName(requester);
        request.setUpdatedAt(LocalDateTime.now());

        return RequestMapper.toDTO(requestRepo.save(request));
    }

    public void deleteRequest(Long id) {
        Request request = requestRepo.findById(id)
                .orElseThrow(() -> new RequestNotFoundException(id));

        requestRepo.delete(request);
    }

    public List<RequestResponseDTO> getRequestsByTopic(String topic) {
        return requestRepo.findByTopicOrderByCreatedAtAsc(topic)
                .stream()
                .map(RequestMapper::toDTO)
                .toList();
    }

    public List<RequestResponseDTO> searchByRequesterName(String name) {
        return requestRepo.findByRequesterNameContainingIgnoreCaseOrderByCreatedAtAsc(name)
                .stream()
                .map(RequestMapper::toDTO)
                .toList();
    }
}
