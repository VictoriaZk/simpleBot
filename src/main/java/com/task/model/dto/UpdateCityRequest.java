package com.task.model.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class UpdateCityRequest {
    @NotNull
    private Long id;

    @NotBlank
    private String description;
}
