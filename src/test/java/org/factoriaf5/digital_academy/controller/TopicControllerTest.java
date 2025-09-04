package org.factoriaf5.digital_academy.controller;

import org.factoriaf5.digital_academy.contract.TopicServiceContract;
import org.factoriaf5.digital_academy.dto.TopicDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TopicController.class)
class TopicControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private TopicServiceContract service;

    @Test
    void testGetAllTopics() throws Exception {
        TopicDTO topic1 = new TopicDTO(1L, "Hardware");
        TopicDTO topic2 = new TopicDTO(2L, "Software");
        List<TopicDTO> topics = Arrays.asList(topic1, topic2);

        when(service.getAllTopics()).thenReturn(topics);

        mockMvc.perform(get("/api/v1/topics")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(topics.size()))
                .andExpect(jsonPath("$[0].name").value("Hardware"))
                .andExpect(jsonPath("$[1].name").value("Software"));
    }
}
