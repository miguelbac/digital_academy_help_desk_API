package org.factoriaf5.digital_academy.contract;

import org.factoriaf5.digital_academy.dto.RequestCreateDTO;
import org.factoriaf5.digital_academy.dto.RequestResponseDTO;

import java.util.List;

public interface RequestServiceContract {

    RequestResponseDTO createRequest(RequestCreateDTO dto);

    List<RequestResponseDTO> getAllRequests();

    RequestResponseDTO updateStatus(Long id, String newStatus, String technician);

    RequestResponseDTO updateDescription(Long id, String newDescription);

    RequestResponseDTO updateTopic(Long id, String newTopic);

    RequestResponseDTO reassignRequester(Long id, String newRequesterName);

    void deleteRequest(Long id);

    List<RequestResponseDTO> getRequestsByTopic(String topicName);

    List<RequestResponseDTO> searchByRequesterName(String requesterName);
}
