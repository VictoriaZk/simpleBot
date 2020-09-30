package com.task.model.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CreateCityRequest {
    @NotBlank
    private String name;

    @NotBlank
    private String description;
}
