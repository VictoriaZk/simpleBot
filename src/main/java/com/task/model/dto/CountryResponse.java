package com.task.model.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class CountryResponse implements Serializable {
    private Long id;
    private String name;
}
