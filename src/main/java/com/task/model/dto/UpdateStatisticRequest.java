package com.task.model.dto;

import lombok.Data;

@Data
public class UpdateStatisticRequest {
    private Long id;

    private Integer amount;

    private Boolean isQuarantineNeeded;

    private String country;
}
