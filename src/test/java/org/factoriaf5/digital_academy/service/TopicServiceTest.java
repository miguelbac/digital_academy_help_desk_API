package org.factoriaf5.digital_academy.service;

import org.factoriaf5.digital_academy.dto.TopicDTO;
import org.factoriaf5.digital_academy.model.Topic;
import org.factoriaf5.digital_academy.repository.TopicRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TopicServiceTest {

    private TopicRepository topicRepo;
    private TopicService service;

    @BeforeEach
    void setUp() {
        topicRepo = mock(TopicRepository.class);
        service = new TopicService(topicRepo);
    }

    @Test
    void getAllTopics_success() {
        Topic t1 = new Topic();
        t1.setId(1L);
        t1.setName("Hardware");

        Topic t2 = new Topic();
        t2.setId(2L);
        t2.setName("Software");

        when(topicRepo.findAll()).thenReturn(List.of(t1, t2));

        List<TopicDTO> result = service.getAllTopics();

        assertEquals(2, result.size());
        assertEquals("Hardware", result.get(0).getName());
        assertEquals("Software", result.get(1).getName());
    }
}
