package com.example.simplespring.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import module java.base;

@Entity
@Table(name = "task")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Task {

    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private UUID id;

    @Column(nullable = false)
    @NonNull
    private String title;

    @Column(nullable = false, length = 50)
    @NonNull
    private String status;

    @Column(nullable = false)
    private int position;

    @Column(length = 50)
    private String category;

    @Column(name = "skipped_count", nullable = false)
    private int skippedCount;

    public Task(@NonNull String title, @NonNull String status, int position, String category) {
        this.title = title;
        this.status = status;
        this.position = position;
        this.category = category;
    }
}
