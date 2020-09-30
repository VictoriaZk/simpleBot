package com.task.service.impl;

import com.task.model.dto.CreateCityRequest;
import com.task.model.dto.CityResponse;
import com.task.model.dto.UpdateCityRequest;

public interface CityService {
    CityResponse create(CreateCityRequest cityRequest);

    void update(UpdateCityRequest updateCityRequest);

    void delete(Long id);
}
