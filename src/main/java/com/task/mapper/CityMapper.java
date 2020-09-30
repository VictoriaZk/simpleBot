package com.task.mapper;

import com.task.model.City;
import com.task.model.dto.CityResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CityMapper extends BaseMapper<City, CityResponse> {
}
