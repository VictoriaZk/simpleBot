package com.task.model.dto;

import lombok.Data;

@Data
public class UpdateHotelRequest {
    private Long id;

    private Integer stars;

    private String name;
}
