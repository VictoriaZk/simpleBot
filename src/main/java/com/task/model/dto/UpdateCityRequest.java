package com.task.model.dto;

import com.task.model.Country;
import com.task.model.Hotel;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
public class UpdateCityRequest {
    @NotNull
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String description;

    @NotBlank
    private String recommendToVisit;

    @NotBlank
    private String notRecommendToVisit;

    private String country;
}
