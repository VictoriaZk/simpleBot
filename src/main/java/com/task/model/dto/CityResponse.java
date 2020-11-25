package com.task.model.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class CityResponse implements Serializable {
    private Long id;
    private String name;
    private String description;
    private String recommendToVisit;
    private String notRecommendToVisit;
    private CountryResponse country;
}
