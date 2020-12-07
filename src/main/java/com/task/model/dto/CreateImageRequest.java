package com.task.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * CreateImageRequest.
 *
 * @author Viktoryia Zhak
 */
@Data
public class CreateImageRequest {
    @NotBlank
    private String url;

    @NotNull
    @JsonProperty("city")
    private String city;

    @NotNull
    @JsonProperty("isVideo")
    private Boolean isVideo;
}
