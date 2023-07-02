package com.example.jobprocessor.service;

import com.example.jobprocessor.dto.Job;
import com.example.jobprocessor.exception.CircularDependencyException;

import java.util.*;

public class TopologicalSort {

    public static List<Job> topologicalSort(final List<Job> jobs) {
        final Map<String, Job> taskMap = new HashMap<>();
        for (Job job : jobs) {
            taskMap.put(job.getName(), job);
        }

        final List<Job> sortedTasks = new ArrayList<>();
        final Set<String> visited = new HashSet<>();
        final Set<String> path = new HashSet<>();
        final Set<String> circularDependencies = new HashSet<>();

        for (Job job : jobs) {
            if (!visited.contains(job.getName())) {
                if (!visit(job, taskMap, visited, path, sortedTasks, circularDependencies)) {
                    throw new CircularDependencyException("Circular dependency detected: " + circularDependencies);
                }
            }
        }

        return sortedTasks;
    }

    private static boolean visit(final Job job,
                                 final Map<String, Job> taskMap,
                                 final Set<String> visited,
                                 final Set<String> path,
                                 final List<Job> sortedTasks,
                                 final Set<String> circularDependencies) {
        visited.add(job.getName());
        path.add(job.getName());

        if (job.getRequires() != null) {
            for (String dependency : job.getRequires()) {
                final Job dependentTask = taskMap.get(dependency);
                if (dependentTask != null) {
                    if (path.contains(dependency)) {
                        circularDependencies.add(dependency);
                        return false; // Circular dependency detected
                    }
                    if (!visited.contains(dependency)) {
                        if (!visit(dependentTask, taskMap, visited, path, sortedTasks, circularDependencies)) {
                            return false; // Circular dependency detected
                        }
                    }
                }
            }
        }

        path.remove(job.getName());
        sortedTasks.add(job);
        return true;
    }
}
