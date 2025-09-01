package org.factoriaf5.digital_academy.service;

import org.factoriaf5.digital_academy.contract.RequestServiceContract;
import org.factoriaf5.digital_academy.dto.RequestCreateDTO;
import org.factoriaf5.digital_academy.dto.RequestResponseDTO;
import org.factoriaf5.digital_academy.mapper.RequestMapper;
import org.factoriaf5.digital_academy.model.Request;
import org.factoriaf5.digital_academy.repository.RequestRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RequestService implements RequestServiceContract {

    private final RequestRepository repository;

    public RequestService(RequestRepository repository) {
        this.repository = repository;
    }

    @Override
    public RequestResponseDTO createRequest(RequestCreateDTO dto) {
        Request request = new Request();
        request.setRequesterName(dto.getRequesterName());
        request.setTopic(dto.getTopic());
        request.setDescription(dto.getDescription());
        request.setStatus("pending");
        request.setCreatedAt(LocalDateTime.now());

        Request saved = repository.save(request);
        return RequestMapper.toDTO(saved);
    }

    @Override
    public List<RequestResponseDTO> getAllRequests() {
        return repository.findAllByOrderByCreatedAtAsc()
                .stream()
                .map(RequestMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public RequestResponseDTO updateStatus(Long id, String newStatus) {
        Request request = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Request not found with id: " + id));
        request.setStatus(newStatus);
        Request saved = repository.save(request);
        return RequestMapper.toDTO(saved);
    }

    @Override
    public List<RequestResponseDTO> getRequestsByTopic(String topicName) {
        return repository.findAll().stream()
                .filter(r -> r.getTopic().equalsIgnoreCase(topicName))
                .map(RequestMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<RequestResponseDTO> searchByRequesterName(String requesterName) {
        return repository.findByRequesterNameContainingIgnoreCase(requesterName)
                .stream()
                .map(RequestMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteRequest(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Request not found with id: " + id);
        }
        repository.deleteById(id);
    }

    @Override
    public RequestResponseDTO updateDescription(Long id, String newDescription) {
        Request request = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Request not found with id: " + id));
        request.setDescription(newDescription);
        Request saved = repository.save(request);
        return RequestMapper.toDTO(saved);
    }
}
