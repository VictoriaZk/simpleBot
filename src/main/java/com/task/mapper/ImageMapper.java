package com.task.mapper;

import com.task.model.Image;
import com.task.model.dto.ImageResponse;
import org.mapstruct.Mapper;

/**
 * ImageMapper.
 *
 * @author Viktoryia Zhak
 */
@Mapper(componentModel = "spring")
public interface ImageMapper extends BaseMapper<Image, ImageResponse> {
}

