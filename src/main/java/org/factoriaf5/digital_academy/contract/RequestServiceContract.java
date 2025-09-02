package org.factoriaf5.digital_academy.contract;

import org.factoriaf5.digital_academy.dto.RequestCreateDTO;
import org.factoriaf5.digital_academy.dto.RequestResponseDTO;

import java.util.List;

public interface RequestServiceContract {
    RequestResponseDTO createRequest(RequestCreateDTO dto);
    List<RequestResponseDTO> getAllRequests();
    RequestResponseDTO updateStatus(Long id, String newStatus);
    List<RequestResponseDTO> getRequestsByTopic(String topicName);
    List<RequestResponseDTO> searchByRequesterName(String requesterName);
    void deleteRequest(Long id);
    RequestResponseDTO updateDescription(Long id, String newDescription);
    RequestResponseDTO reassignRequester(Long id, String newRequesterName);
    RequestResponseDTO updateTopic(Long id, String newTopic);
}
