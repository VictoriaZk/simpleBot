package com.task.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * ImageResponse.
 *
 * @author Viktoryia Zhak
 */
@Data
public class ImageResponse implements Serializable {
    private Long id;

    private String url;

    private Boolean isVideo;

    @JsonProperty("city_id")
    private CityResponse city;
}
