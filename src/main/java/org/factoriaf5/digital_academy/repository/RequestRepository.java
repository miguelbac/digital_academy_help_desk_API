package org.factoriaf5.digital_academy.repository;

import org.factoriaf5.digital_academy.model.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface RequestRepository extends JpaRepository<Request, Long> {

    // Todas las solicitudes ordenadas por fecha de creación ascendente
    List<Request> findAllByOrderByCreatedAtAsc();
}
