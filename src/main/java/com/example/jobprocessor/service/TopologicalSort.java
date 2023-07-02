package com.example.jobprocessor.service;

import com.example.jobprocessor.dto.Job;

import java.util.*;

public class TopologicalSort {

    public static List<Job> topologicalSort(final List<Job> jobs) {
        final Map<String, Job> taskMap = new HashMap<>();
        for (Job job : jobs) {
            taskMap.put(job.getName(), job);
        }

        final List<Job> sortedTasks = new ArrayList<>();
        final Set<String> visited = new HashSet<>();

        for (Job job : jobs) {
            if (!visited.contains(job.getName())) {
                visit(job, taskMap, visited, sortedTasks);
            }
        }

        return sortedTasks;
    }

    private static void visit(final Job job,
                              final Map<String, Job> taskMap,
                              final Set<String> visited,
                              final List<Job> sortedTasks) {
        visited.add(job.getName());

        if (job.getRequires() != null) {
            for (String dependency : job.getRequires()) {
                final Job dependentTask = taskMap.get(dependency);
                if (dependentTask != null && !visited.contains(dependency)) {
                    visit(dependentTask, taskMap, visited, sortedTasks);
                }
            }
        }

        sortedTasks.add(job);
    }
}
