package com.example.jobprocessor.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class Request {
    private List<Job> jobs;
}
