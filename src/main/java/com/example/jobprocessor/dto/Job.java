package com.example.jobprocessor.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import java.util.List;

import static com.example.jobprocessor.util.StringValidator.isNotNullNorEmpty;

@Getter
@EqualsAndHashCode
public class Job {
    @NotBlank(message = "The name is required.")
    private final String name;
    @NotBlank(message = "The command is required.")
    private final String command;
    private final List<String> requires;

    public Job(final String name, final String command, final List<String> requires) {
        if (isNotNullNorEmpty(name) && isNotNullNorEmpty(command)) {
            this.name = name;
            this.command = command;
            this.requires = requires;
        } else {
            throw new RuntimeException("Vertex name/command cannot be null or an empty string!");
        }
    }
}
