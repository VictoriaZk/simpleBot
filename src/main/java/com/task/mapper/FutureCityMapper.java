package com.task.mapper;

import com.task.model.FutureCity;
import com.task.model.dto.FutureCityResponse;
import org.mapstruct.Mapper;

/**
 * FutureCityMapper.
 *
 * @author Viktoryia Zhak
 */
@Mapper(componentModel = "spring")
public interface FutureCityMapper extends BaseMapper<FutureCity, FutureCityResponse> {
}

