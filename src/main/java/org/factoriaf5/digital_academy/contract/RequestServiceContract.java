package org.factoriaf5.digital_academy.contract;

import org.factoriaf5.digital_academy.dto.RequestCreateDTO;
import org.factoriaf5.digital_academy.dto.RequestResponseDTO;
import java.util.List;

public interface RequestServiceContract {

    RequestResponseDTO createRequest(RequestCreateDTO dto);

    List<RequestResponseDTO> getAllRequests();
}
