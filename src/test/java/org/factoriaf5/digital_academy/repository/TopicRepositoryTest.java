package org.factoriaf5.digital_academy.repository;

import org.factoriaf5.digital_academy.model.Topic;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class TopicRepositoryTest {

    @Autowired
    private TopicRepository topicRepository;

    @BeforeEach
    void clean() {
        topicRepository.deleteAll(); // Limpiar tabla antes de cada test
    }

    @Test
    void testSaveAndFindById() {
        Topic topic = new Topic();
        topic.setName("Hardware-" + UUID.randomUUID()); // Nombre único
        topicRepository.save(topic);

        Topic found = topicRepository.findById(topic.getId()).orElse(null);
        assertThat(found).isNotNull();
        assertThat(found.getName()).startsWith("Hardware-");
    }

    @Test
    void testExistsByName() {
        String uniqueName = "Software-" + UUID.randomUUID();
        Topic topic = new Topic();
        topic.setName(uniqueName);
        topicRepository.save(topic);

        assertThat(topicRepository.existsByName(uniqueName)).isTrue();
        assertThat(topicRepository.existsByName("Network-" + UUID.randomUUID())).isFalse();
    }

    @Test
    void testFindAll() {
        Topic topic1 = new Topic();
        topic1.setName("Networking-" + UUID.randomUUID());
        Topic topic2 = new Topic();
        topic2.setName("Database-" + UUID.randomUUID());
        topicRepository.save(topic1);
        topicRepository.save(topic2);

        List<Topic> allTopics = topicRepository.findAll();
        assertThat(allTopics).hasSize(2)
                .extracting(Topic::getName)
                .allSatisfy(name -> assertThat(name).isNotBlank());
    }
}
