package com.example.jobprocessor.controller;

import com.example.jobprocessor.dto.Request;
import com.example.jobprocessor.service.JobService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("v1/job-processor")
public class JobController {

    private final JobService jobService;

    public JobController(final JobService jobService) {
        this.jobService = jobService;
    }

    @PostMapping("/sort-jobs")
    public String getTasksSorted(@Valid @RequestBody final Request request) {
        return jobService.getTasksSorted(request);
    }
}
