package com.example.simplespring.service;

import com.example.simplespring.dto.CreateTaskRequest;
import com.example.simplespring.dto.TaskResponse;
import com.example.simplespring.entity.Task;
import com.example.simplespring.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TaskService {

    static final String INITIAL_STATUS = "In progress";

    private final TaskRepository taskRepository;

    @Transactional
    public TaskResponse create(CreateTaskRequest request) {
        var task = new Task(request.title(), INITIAL_STATUS, taskRepository.findNextPosition(), request.category());
        return TaskResponse.from(taskRepository.save(task));
    }
}
