package com.task.model.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class CreateStatisticRequest {
    @NotNull
    private Long amount;

    @NotNull
    private Integer day;

    @NotNull
    private Boolean isQuarantineNeeded;

    @NotBlank
    private String country;
}
