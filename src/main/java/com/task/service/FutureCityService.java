package com.task.service;

import com.task.mapper.FutureCityMapper;
import com.task.model.dto.FutureCityResponse;
import com.task.repository.CityRepository;
import com.task.repository.FutureCityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * FutureCityService.
 *
 * @author Viktoryia Zhak
 */
@Service
@RequiredArgsConstructor
public class FutureCityService {
    private final FutureCityRepository futureCityRepository;

    private final FutureCityMapper mapper;

    public List<FutureCityResponse> getAll() {
        return futureCityRepository.findAll().stream()
                .map(mapper::entityToDto)
                .sorted(Comparator.comparing(FutureCityResponse::getAmountOfRequests).reversed())
        .collect(Collectors.toList());
    }
}
