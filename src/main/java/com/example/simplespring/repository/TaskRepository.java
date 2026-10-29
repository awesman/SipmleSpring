package com.example.simplespring.repository;

import com.example.simplespring.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import module java.base;

public interface TaskRepository extends JpaRepository<Task, UUID> {

    @Query("select coalesce(max(t.position), -1) + 1 from Task t")
    int findNextPosition();
}
