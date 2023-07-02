package com.example.jobprocessor.service;

import com.example.jobprocessor.dto.Job;
import com.example.jobprocessor.dto.Request;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.jobprocessor.service.TopologicalSort.topologicalSort;

@Service
public class JobService {

    public String getTasksSorted(final Request request) {
        // Perform topological sorting
        final List<Job> sortedTasks = topologicalSort(request.getJobs());
        // Return the command as string for a response
        return getCommandString(sortedTasks);
    }

    private static String getCommandString(final List<Job> sortedTasks) {
        final StringBuilder sb = new StringBuilder();
        // Append the shebang line to make the script executable directly from the command line
        // without explicitly specifying the interpreter
        sb.append("#!/usr/bin/env bash");

        // Append the sorted commands
        for (Job job : sortedTasks) {
            sb.append(job.getCommand());
            sb.append('\n');
        }

        // Return one whole command
        return sb.toString();
    }
}
