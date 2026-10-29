package com.example.simplespring.controller;

import com.example.simplespring.dto.CreateTaskRequest;
import com.example.simplespring.dto.TaskResponse;
import com.example.simplespring.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @PostMapping("/task")
    @ResponseStatus(HttpStatus.CREATED)
    public TaskResponse create(@RequestBody CreateTaskRequest request) {
        return taskService.create(request);
    }
}
