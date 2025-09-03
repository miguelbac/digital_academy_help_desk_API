package org.factoriaf5.digital_academy.repository;

import org.factoriaf5.digital_academy.model.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicRepository extends JpaRepository<Topic, Long> {
    boolean existsByName(String name);
}
