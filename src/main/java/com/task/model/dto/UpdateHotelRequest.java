package com.task.model.dto;

import lombok.Data;

@Data
public class UpdateHotelRequest {
    private Long id;

    private String cost;

    private Integer stars;

    private String name;

    private Double rating;
}
