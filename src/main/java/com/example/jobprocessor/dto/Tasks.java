package com.example.jobprocessor.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class Tasks {
    private List<Job> tasks;

    public Tasks(final List<Job> tasks) {
        this.tasks = tasks;
    }
}
