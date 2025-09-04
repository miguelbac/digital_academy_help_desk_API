package org.factoriaf5.digital_academy.repository;

import org.factoriaf5.digital_academy.model.Request;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class RequestRepositoryTest {

    @Autowired
    private RequestRepository requestRepository;

    @BeforeEach
    void clean() {
        requestRepository.deleteAll(); // Limpiar tabla antes de cada test
    }

    @Test
    void testSaveAndFindById() {
        Request request = new Request();
        request.setRequesterName("Alice");
        request.setTopic("Hardware");
        request.setDescription("Issue 1");
        request.setStatus("pending");
        request.setCreatedAt(LocalDateTime.now());

        requestRepository.save(request);

        Request found = requestRepository.findById(request.getId()).orElse(null);
        assertThat(found).isNotNull();
        assertThat(found.getRequesterName()).isEqualTo("Alice");
    }

    @Test
    void testFindAllByOrderByCreatedAtAsc() throws InterruptedException {
        Request request1 = new Request();
        request1.setRequesterName("Bob");
        request1.setTopic("Software");
        request1.setDescription("Issue A");
        request1.setStatus("pending");
        request1.setCreatedAt(LocalDateTime.now());
        requestRepository.save(request1);

        Thread.sleep(10); // asegurar diferencia de tiempo

        Request request2 = new Request();
        request2.setRequesterName("Carol");
        request2.setTopic("Networking");
        request2.setDescription("Issue B");
        request2.setStatus("pending");
        request2.setCreatedAt(LocalDateTime.now());
        requestRepository.save(request2);

        List<Request> requests = requestRepository.findAllByOrderByCreatedAtAsc();
        assertThat(requests).hasSize(2);
        assertThat(requests.get(0).getRequesterName()).isEqualTo("Bob");
        assertThat(requests.get(1).getRequesterName()).isEqualTo("Carol");
    }

    @Test
    void testFindByRequesterNameContainingIgnoreCaseOrderByCreatedAtAsc() {
        Request request = new Request();
        request.setRequesterName("Alice");
        request.setTopic("Hardware");
        request.setDescription("Issue X");
        request.setStatus("pending");
        request.setCreatedAt(LocalDateTime.now());
        requestRepository.save(request);

        List<Request> results = requestRepository.findByRequesterNameContainingIgnoreCaseOrderByCreatedAtAsc("alice");
        assertThat(results).hasSize(1).extracting(Request::getRequesterName).containsExactly("Alice");
    }

    @Test
    void testFindByTopicOrderByCreatedAtAsc() {
        Request request = new Request();
        request.setRequesterName("Dave");
        request.setTopic("Networking");
        request.setDescription("Issue Y");
        request.setStatus("pending");
        request.setCreatedAt(LocalDateTime.now());
        requestRepository.save(request);

        List<Request> results = requestRepository.findByTopicOrderByCreatedAtAsc("Networking");
        assertThat(results).hasSize(1).extracting(Request::getTopic).containsExactly("Networking");
    }
}
