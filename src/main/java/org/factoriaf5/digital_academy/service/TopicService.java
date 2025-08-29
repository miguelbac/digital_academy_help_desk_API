package org.factoriaf5.digital_academy.service;

import org.factoriaf5.digital_academy.contract.TopicServiceContract;
import org.factoriaf5.digital_academy.dto.TopicDTO;
import org.factoriaf5.digital_academy.repository.TopicRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TopicService implements TopicServiceContract {

    private final TopicRepository repository;

    public TopicService(TopicRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<TopicDTO> getAllTopics() {
        return repository.findAll()
                .stream()
                .map(topic -> new TopicDTO(topic.getId(), topic.getName()))
                .collect(Collectors.toList());
    }
}
