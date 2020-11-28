package com.task.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class CreateHotelRequest {
    @NotNull
    private Integer stars;

    @NotBlank
    private String name;

    @NotBlank
    private String cost;

    @NotNull
    @JsonProperty("city")
    private String city;
}
