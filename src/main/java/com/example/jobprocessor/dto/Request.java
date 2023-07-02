package com.example.jobprocessor.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class Request {
    private List<Job> tasks;

    public Request(final List<Job> tasks) {
        this.tasks = tasks;
    }
}
