package com.example.simplespring.dto;

import com.example.simplespring.entity.Task;

import module java.base;

public record TaskResponse(UUID id, String title, String status, int position, String category, int skippedCount) {

    public static TaskResponse from(Task task) {
        return new TaskResponse(
                task.getId(),
                task.getTitle(),
                task.getStatus(),
                task.getPosition(),
                task.getCategory(),
                task.getSkippedCount());
    }
}
