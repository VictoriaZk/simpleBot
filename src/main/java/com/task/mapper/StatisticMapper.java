package com.task.mapper;

import com.task.model.Statistic;
import com.task.model.dto.StatisticResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StatisticMapper extends BaseMapper<Statistic, StatisticResponse> {
}
