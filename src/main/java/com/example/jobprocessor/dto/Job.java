package com.example.jobprocessor.dto;

import com.example.jobprocessor.exception.BusinessLogicValidationException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import java.util.List;

import static com.example.jobprocessor.util.StringValidator.isNotNullNorEmpty;

@Getter
@EqualsAndHashCode
@ToString
public class Job {
    @NotBlank(message = "The name is required.")
    private final String name;
    @NotBlank(message = "The command is required.")
    private final String command;
    private final List<String> requires;

    @JsonCreator
    public Job(@JsonProperty(value = "name") final String name,
               @JsonProperty(value = "command") final String command,
               @JsonProperty(value = "requires") final List<String> requires) {
        if (isNotNullNorEmpty(name) && isNotNullNorEmpty(command)) {
            this.name = name;
            this.command = command;
            this.requires = requires;
        } else {
            throw new BusinessLogicValidationException("Job's 'name' and 'command' properties can be neither null nor an empty string!");
        }
    }
}
