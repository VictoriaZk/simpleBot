package com.task.mapper;

import com.task.model.Hotel;
import com.task.model.dto.HotelResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface HotelMapper extends BaseMapper<Hotel, HotelResponse> {
}
