package service;

import com.example.jobprocessor.dto.Job;
import com.example.jobprocessor.dto.Request;
import com.example.jobprocessor.service.TopologicalSort;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TopologicalSortIntegrationTest {
    final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void testSimpleTopologicalSort() throws IOException {
        final List<Job> jobs = getJobsFromJsonFile("one-possible-topological-sort.json");

        final List<Job> sortedTasks = TopologicalSort.topologicalSort(jobs);

        assertEquals(4, sortedTasks.size());

        // Verify the order of jobs in the sorted list
        assertEquals("job-1", sortedTasks.get(0).getName());
        assertEquals("job-3", sortedTasks.get(1).getName());
        assertEquals("job-2", sortedTasks.get(2).getName());
        assertEquals("job-4", sortedTasks.get(3).getName());
    }+

    @Test
    public void testCircularDependencyTopologicalSort() throws IOException {
        final List<Job> jobs = getJobsFromJsonFile("circular-dependency-topological-sort.json");

        assertThrows(IllegalArgumentException.class, () -> TopologicalSort.topologicalSort(jobs));
    }

    @Test
    public void testSophisticatedCircularDependencyTopologicalSort() throws IOException {
        final List<Job> jobs = getJobsFromJsonFile("sophisticated-circular-dependency-topological-sort.json");

        assertThrows(IllegalArgumentException.class, () -> TopologicalSort.topologicalSort(jobs));
    }

    private List<Job> getJobsFromJsonFile(final String filename) throws IOException {
        final ClassLoader classLoader = getClass().getClassLoader();
        final URL resourceUrl = classLoader.getResource(filename);
        final File file = new File(resourceUrl.getFile());
        final String json = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
        final Request request = objectMapper.readValue(json, Request.class);
        return request.getJobs();
    }
}
