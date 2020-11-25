package com.task.model.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class StatisticResponse implements Serializable {
    private Long id;

    private Integer amount;

    private Boolean isQuarantineNeeded;

    private CountryResponse country;
}
