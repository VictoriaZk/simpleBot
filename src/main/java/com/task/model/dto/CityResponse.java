package com.task.model.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class CityResponse implements Serializable {
    private String name;
    private String description;
}
