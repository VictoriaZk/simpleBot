package com.task.mapper;

import com.task.model.Country;
import com.task.model.dto.CountryResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CountryMapper extends BaseMapper<Country, CountryResponse> {
}
