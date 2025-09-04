package org.factoriaf5.digital_academy.service;

import org.factoriaf5.digital_academy.dto.RequestCreateDTO;
import org.factoriaf5.digital_academy.dto.RequestResponseDTO;
import org.factoriaf5.digital_academy.exception.InvalidStatusException;
import org.factoriaf5.digital_academy.exception.RequestNotFoundException;
import org.factoriaf5.digital_academy.exception.TopicNotFoundException;
import org.factoriaf5.digital_academy.model.Request;
import org.factoriaf5.digital_academy.repository.RequestRepository;
import org.factoriaf5.digital_academy.repository.TopicRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RequestServiceTest {

    private RequestRepository requestRepo;
    private TopicRepository topicRepo;
    private RequestService service;

    @BeforeEach
    void setUp() {
        requestRepo = mock(RequestRepository.class);
        topicRepo = mock(TopicRepository.class);
        service = new RequestService(requestRepo, topicRepo);
    }

    @Test
    void createRequest_success() {
        RequestCreateDTO dto = new RequestCreateDTO("Alice", "Hardware", "Laptop issue");
        when(topicRepo.existsByName("Hardware")).thenReturn(true);

        Request savedRequest = new Request();
        savedRequest.setId(1L);
        savedRequest.setRequesterName("Alice");
        savedRequest.setTopic("Hardware");
        savedRequest.setDescription("Laptop issue");
        savedRequest.setStatus("pending");

        when(requestRepo.save(any(Request.class))).thenReturn(savedRequest);

        RequestResponseDTO result = service.createRequest(dto);

        assertNotNull(result);
        assertEquals("Alice", result.getRequesterName());
        assertEquals("Hardware", result.getTopic());
        assertEquals("Laptop issue", result.getDescription());
        assertEquals("pending", result.getStatus());
        verify(requestRepo, times(1)).save(any(Request.class));
    }

    @Test
    void createRequest_topicNotFound_throwsException() {
        RequestCreateDTO dto = new RequestCreateDTO("Bob", "NonExistent", "Problem");
        when(topicRepo.existsByName("NonExistent")).thenReturn(false);

        assertThrows(TopicNotFoundException.class, () -> service.createRequest(dto));
        verify(requestRepo, never()).save(any());
    }

    @Test
    void updateStatus_success() {
        Request request = new Request();
        request.setId(1L);
        request.setStatus("pending");

        when(requestRepo.findById(1L)).thenReturn(Optional.of(request));
        when(requestRepo.save(any(Request.class))).thenAnswer(i -> i.getArgument(0));

        RequestResponseDTO updated = service.updateStatus(1L, "attended");

        assertEquals("attended", updated.getStatus());
    }

    @Test
    void updateStatus_invalidStatus_throwsException() {
        Request request = new Request();
        request.setId(1L);

        when(requestRepo.findById(1L)).thenReturn(Optional.of(request));

        assertThrows(InvalidStatusException.class, () -> service.updateStatus(1L, "wrong"));
    }

    @Test
    void updateStatus_requestNotFound_throwsException() {
        when(requestRepo.findById(1L)).thenReturn(Optional.empty());
        assertThrows(RequestNotFoundException.class, () -> service.updateStatus(1L, "pending"));
    }

    @Test
    void deleteRequest_success() {
        Request request = new Request();
        request.setId(1L);

        when(requestRepo.findById(1L)).thenReturn(Optional.of(request));

        service.deleteRequest(1L);

        verify(requestRepo, times(1)).delete(request);
    }

    @Test
    void deleteRequest_notFound_throwsException() {
        when(requestRepo.findById(1L)).thenReturn(Optional.empty());
        assertThrows(RequestNotFoundException.class, () -> service.deleteRequest(1L));
    }

    @Test
    void getAllRequests_success() {
        Request request1 = new Request();
        request1.setId(1L);
        request1.setRequesterName("Alice");

        Request request2 = new Request();
        request2.setId(2L);
        request2.setRequesterName("Bob");

        when(requestRepo.findAllByOrderByCreatedAtAsc()).thenReturn(List.of(request1, request2));

        List<RequestResponseDTO> result = service.getAllRequests();
        assertEquals(2, result.size());
    }

    // ------------------ Nuevos tests para subir coverage ------------------

    @Test
    void updateDescription_success() {
        Request request = new Request();
        request.setId(1L);
        request.setDescription("Old description");

        when(requestRepo.findById(1L)).thenReturn(Optional.of(request));
        when(requestRepo.save(any(Request.class))).thenAnswer(i -> i.getArgument(0));

        RequestResponseDTO updated = service.updateDescription(1L, "New description");

        assertEquals("New description", updated.getDescription());
    }

    @Test
    void updateDescription_requestNotFound_throwsException() {
        when(requestRepo.findById(1L)).thenReturn(Optional.empty());
        assertThrows(RequestNotFoundException.class, () -> service.updateDescription(1L, "desc"));
    }

    @Test
    void reassignRequester_success() {
        Request request = new Request();
        request.setId(1L);
        request.setRequesterName("OldName");

        when(requestRepo.findById(1L)).thenReturn(Optional.of(request));
        when(requestRepo.save(any(Request.class))).thenAnswer(i -> i.getArgument(0));

        RequestResponseDTO updated = service.reassignRequester(1L, "NewName");
        assertEquals("NewName", updated.getRequesterName());
    }

    @Test
    void reassignRequester_requestNotFound_throwsException() {
        when(requestRepo.findById(1L)).thenReturn(Optional.empty());
        assertThrows(RequestNotFoundException.class, () -> service.reassignRequester(1L, "Someone"));
    }

    @Test
    void updateTopic_success() {
        Request request = new Request();
        request.setId(1L);
        request.setTopic("OldTopic");

        when(topicRepo.existsByName("NewTopic")).thenReturn(true);
        when(requestRepo.findById(1L)).thenReturn(Optional.of(request));
        when(requestRepo.save(any(Request.class))).thenAnswer(i -> i.getArgument(0));

        RequestResponseDTO updated = service.updateTopic(1L, "NewTopic");
        assertEquals("NewTopic", updated.getTopic());
    }

    @Test
    void updateTopic_requestNotFound_throwsException() {
        when(topicRepo.existsByName("NewTopic")).thenReturn(true);
        when(requestRepo.findById(1L)).thenReturn(Optional.empty());
        assertThrows(RequestNotFoundException.class, () -> service.updateTopic(1L, "NewTopic"));
    }

    @Test
    void updateTopic_topicNotFound_throwsException() {
        when(topicRepo.existsByName("UnknownTopic")).thenReturn(false);
        assertThrows(TopicNotFoundException.class, () -> service.updateTopic(1L, "UnknownTopic"));
    }

    @Test
    void getRequestsByTopic_success() {
        Request request = new Request();
        request.setId(1L);
        request.setTopic("Hardware");

        when(topicRepo.existsByName("Hardware")).thenReturn(true);
        when(requestRepo.findByTopicOrderByCreatedAtAsc("Hardware")).thenReturn(List.of(request));

        List<RequestResponseDTO> result = service.getRequestsByTopic("Hardware");
        assertEquals(1, result.size());
        assertEquals("Hardware", result.get(0).getTopic());
    }

    @Test
    void getRequestsByTopic_topicNotFound_throwsException() {
        when(topicRepo.existsByName("Unknown")).thenReturn(false);
        assertThrows(TopicNotFoundException.class, () -> service.getRequestsByTopic("Unknown"));
    }

    @Test
    void searchByRequesterName_success() {
        Request request1 = new Request();
        request1.setId(1L);
        request1.setRequesterName("Alice");

        Request request2 = new Request();
        request2.setId(2L);
        request2.setRequesterName("Alice Smith");

        when(requestRepo.findByRequesterNameContainingIgnoreCaseOrderByCreatedAtAsc("Alice"))
                .thenReturn(List.of(request1, request2));

        List<RequestResponseDTO> result = service.searchByRequesterName("Alice");
        assertEquals(2, result.size());
        assertTrue(result.stream().allMatch(r -> r.getRequesterName().contains("Alice")));
    }
}
