package com.example.simplespring.dto;

public record CreateTaskRequest(String title, String category) {

    public CreateTaskRequest {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Task title is mandatory");
        }
        title = title.strip();
        category = category == null || category.isBlank() ? null : category.strip();
    }
}
