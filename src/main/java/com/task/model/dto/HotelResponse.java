package com.task.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class HotelResponse implements Serializable {
    private Long id;

    @JsonProperty("stars")
    private Integer amountOfStars;

    private String cost;
    
    private String name;

    @JsonProperty("city_id")
    private CityResponse city;
}
