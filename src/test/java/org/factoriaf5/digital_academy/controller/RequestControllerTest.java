package org.factoriaf5.digital_academy.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.factoriaf5.digital_academy.contract.RequestServiceContract;
import org.factoriaf5.digital_academy.dto.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(RequestController.class)
class RequestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private RequestServiceContract service;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testGetAllRequests() throws Exception {
        RequestResponseDTO dto = new RequestResponseDTO(
                1L, "Alice", "Laptop issue", "Issue description", "pending", "Technician1", null, null
        );
        when(service.getAllRequests()).thenReturn(Arrays.asList(dto));

        mockMvc.perform(get("/api/v1/requests"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].requesterName").value("Alice"));
    }

@Test
void testCreateRequest() throws Exception {
    RequestCreateDTO createDTO = new RequestCreateDTO("Bob", "Software", "App not working");
    RequestResponseDTO responseDTO = new RequestResponseDTO(
            2L, "Bob", "Software", "App not working", "pending", null, null, null
    );

    when(service.createRequest(any(RequestCreateDTO.class))).thenReturn(responseDTO);

    mockMvc.perform(post("/api/v1/requests")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(createDTO)))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.requesterName").value("Bob"))
            .andExpect(jsonPath("$.topic").value("Software"))
            .andExpect(jsonPath("$.description").value("App not working"))
            .andExpect(jsonPath("$.status").value("pending"));
}


    @Test
    void testUpdateDescription() throws Exception {
        UpdateDescriptionDTO dto = new UpdateDescriptionDTO("New description");
        RequestResponseDTO responseDTO = new RequestResponseDTO(
                1L, "Alice", "Laptop issue", "New description", "pending", "Technician1", null, null
        );

        when(service.updateDescription(1L, "New description")).thenReturn(responseDTO);

        mockMvc.perform(patch("/api/v1/requests/1/description")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.description").value("New description"));
    }

    @Test
    void testUpdateStatus() throws Exception {
        UpdateStatusDTO dto = new UpdateStatusDTO("attended");
        RequestResponseDTO responseDTO = new RequestResponseDTO(
                1L, "Alice", "Laptop issue", "Issue description", "attended", "Technician1", null, null
        );

        when(service.updateStatus(1L, "attended")).thenReturn(responseDTO);

        mockMvc.perform(patch("/api/v1/requests/1/status")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("attended"));
    }

    @Test
    void testUpdateTopic() throws Exception {
        UpdateTopicDTO dto = new UpdateTopicDTO("Network");
        RequestResponseDTO responseDTO = new RequestResponseDTO(
                1L, "Alice", "Network", "Issue description", "pending", "Technician1", null, null
        );

        when(service.updateTopic(1L, "Network")).thenReturn(responseDTO);

        mockMvc.perform(patch("/api/v1/requests/1/topic")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.topic").value("Network"));
    }
}
