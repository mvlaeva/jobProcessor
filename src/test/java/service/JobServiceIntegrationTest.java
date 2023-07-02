package service;

import com.example.jobprocessor.dto.Request;
import com.example.jobprocessor.exception.CircularDependencyException;
import com.example.jobprocessor.service.JobService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class JobServiceIntegrationTest {
    final ObjectMapper objectMapper = new ObjectMapper();

    final JobService jobService = new JobService();

    @Test
    public void testSimpleTopologicalSort() throws IOException {
        final String expected = "#!/usr/bin/env bash\n\n" +
                "touch /tmp/file1\n" +
                "echo 'Hello World!' > /tmp/file1\n" +
                "cat /tmp/file1\n" +
                "rm /tmp/file1\n";
        final Request request = getRequestFromJsonFile("one-possible-topological-sort.json");

        final String result = jobService.getTasksSorted(request);
        assertEquals(expected, result);
    }

    @Test
    public void testCircularDependencyTopologicalSort() throws IOException {
        final Request request = getRequestFromJsonFile("circular-dependency-topological-sort.json");

        assertThrows(CircularDependencyException.class, () -> jobService.getTasksSorted(request));
    }

    @Test
    public void testSophisticatedCircularDependencyTopologicalSort() throws IOException {
        final Request request = getRequestFromJsonFile("sophisticated-circular-dependency-topological-sort.json");

        assertThrows(CircularDependencyException.class, () -> jobService.getTasksSorted(request));
    }

    private Request getRequestFromJsonFile(final String filename) throws IOException {
        final ClassLoader classLoader = getClass().getClassLoader();
        final URL resourceUrl = classLoader.getResource(filename);
        final File file = new File(resourceUrl.getFile());
        final String json = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
        return objectMapper.readValue(json, Request.class);
    }
}
