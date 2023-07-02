package com.example.jobprocessor.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class Request {
    @JsonProperty(value = "tasks")
    private List<Job> jobs;
}
