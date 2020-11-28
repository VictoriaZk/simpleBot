package com.task.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * FutureCityresponse.
 *
 * @author Viktoryia Zhak
 */
@Data
public class FutureCityResponse implements Serializable {
    private String name;

    @JsonProperty("requests")
    private Integer amountOfRequests;
}
