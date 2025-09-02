package org.factoriaf5.digital_academy.service;

import org.factoriaf5.digital_academy.contract.RequestServiceContract;
import org.factoriaf5.digital_academy.dto.RequestCreateDTO;
import org.factoriaf5.digital_academy.dto.RequestResponseDTO;
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
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RequestService implements RequestServiceContract {

    private final RequestRepository repository;
    private final TopicRepository topicRepository;
    private static final Set<String> VALID_STATUSES = Set.of("pending", "in_progress", "attended");

    public RequestService(RequestRepository repository, TopicRepository topicRepository) {
        this.repository = repository;
        this.topicRepository = topicRepository;
    }

    @Override
    public RequestResponseDTO createRequest(RequestCreateDTO dto) {
        if (!topicRepository.existsByName(dto.getTopic())) {
            throw new TopicNotFoundException(dto.getTopic());
        }

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
                .orElseThrow(() -> new RequestNotFoundException(id));

        if (!VALID_STATUSES.contains(newStatus.toLowerCase())) {
            throw new InvalidStatusException(newStatus);
        }

        request.setStatus(newStatus.toLowerCase());
        return RequestMapper.toDTO(repository.save(request));
    }

    @Override
    public List<RequestResponseDTO> getRequestsByTopic(String topicName) {
        if (!topicRepository.existsByName(topicName)) {
            throw new TopicNotFoundException(topicName);
        }

        return repository.findByTopicOrderByCreatedAtAsc(topicName)
                .stream()
                .map(RequestMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<RequestResponseDTO> searchByRequesterName(String requesterName) {
        return repository.findByRequesterNameContainingIgnoreCaseOrderByCreatedAtAsc(requesterName)
                .stream()
                .map(RequestMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteRequest(Long id) {
        Request request = repository.findById(id)
                .orElseThrow(() -> new RequestNotFoundException(id));

        repository.delete(request);
    }

    @Override
    public RequestResponseDTO updateDescription(Long id, String newDescription) {
        Request request = repository.findById(id)
                .orElseThrow(() -> new RequestNotFoundException(id));

        request.setDescription(newDescription);
        return RequestMapper.toDTO(repository.save(request));
    }

    @Override
    public RequestResponseDTO reassignRequester(Long id, String newRequesterName) {
        Request request = repository.findById(id)
                .orElseThrow(() -> new RequestNotFoundException(id));

        request.setRequesterName(newRequesterName);
        return RequestMapper.toDTO(repository.save(request));
    }

    @Override
    public RequestResponseDTO updateTopic(Long id, String newTopic) {
        if (!topicRepository.existsByName(newTopic)) {
            throw new TopicNotFoundException(newTopic);
        }

        Request request = repository.findById(id)
                .orElseThrow(() -> new RequestNotFoundException(id));

        request.setTopic(newTopic);
        return RequestMapper.toDTO(repository.save(request));
    }
}
