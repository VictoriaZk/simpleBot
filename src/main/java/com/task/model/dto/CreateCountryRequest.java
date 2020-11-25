package com.task.model.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CreateCountryRequest {
    @NotBlank
    private String name;
}
