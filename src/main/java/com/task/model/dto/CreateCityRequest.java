package com.task.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class CreateCityRequest {
    @NotBlank
    private String name;

    @NotBlank
    private String description;

    @NotNull
    private String country;

    @NotBlank
    private String recommendToVisit;

    @NotBlank
    private String notRecommendToVisit;
}
