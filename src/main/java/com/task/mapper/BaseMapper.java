package com.task.mapper;

public interface BaseMapper<E, D> {
    D entityToDto(E entity);
}
