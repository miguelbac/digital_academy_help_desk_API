package org.factoriaf5.digital_academy.mapper;

import org.factoriaf5.digital_academy.dto.TopicDTO;
import org.factoriaf5.digital_academy.model.Topic;

public class TopicMapper {

    public static TopicDTO toDTO(Topic topic) {
        return new TopicDTO(topic.getId(), topic.getName());
    }
}
