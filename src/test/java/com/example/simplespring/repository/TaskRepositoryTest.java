package com.example.simplespring.repository;

import com.example.simplespring.entity.Task;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.postgresql.PostgreSQLContainer;

import module java.base;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest(properties = "spring.flyway.enabled=true")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Testcontainers
class TaskRepositoryTest {

    @Container
    @ServiceConnection
    static final PostgreSQLContainer postgres = new PostgreSQLContainer("postgres:18");

    @Autowired
    private TaskRepository taskRepository;

    @Test
    void savesTaskAndGeneratesId() {
        var saved = taskRepository.saveAndFlush(new Task("write tests", "todo", 1, "work"));

        assertThat(saved.getId()).isNotNull();
        assertThat(saved.getSkippedCount()).isZero();
    }

    @Test
    void findsSavedTaskById() {
        var saved = taskRepository.saveAndFlush(new Task("find me", "todo", 2, null));

        Optional<Task> found = taskRepository.findById(saved.getId());

        assertThat(found).hasValueSatisfying(task -> {
            assertThat(task.getTitle()).isEqualTo("find me");
            assertThat(task.getStatus()).isEqualTo("todo");
            assertThat(task.getPosition()).isEqualTo(2);
            assertThat(task.getCategory()).isNull();
        });
    }

    @Test
    void findsSeededTaskFromMigration() {
        assertThat(taskRepository.findAll())
                .extracting(Task::getTitle)
                .contains("init task");
    }

    @Test
    void deletesTask() {
        var saved = taskRepository.saveAndFlush(new Task("delete me", "todo", 3, "home"));

        taskRepository.deleteById(saved.getId());
        taskRepository.flush();

        assertThat(taskRepository.findById(saved.getId())).isEmpty();
    }
}
