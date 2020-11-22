package com.task.service.impl.impl;

import com.task.mapper.CityMapper;
import com.task.model.City;
import com.task.model.dto.CreateCityRequest;
import com.task.model.dto.CityResponse;
import com.task.model.dto.UpdateCityRequest;
import com.task.repository.CityRepository;
import com.task.service.impl.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CityServiceImpl implements CityService {
    private final CityRepository cityRepository;
    private final CityMapper cityMapper;

    @Override
    public CityResponse create(CreateCityRequest cityRequest) {
        City newCity = City.builder()
                .name(cityRequest.getName())
                .description(cityRequest.getDescription())
                .build();

        cityRepository.save(newCity);

        return cityMapper.entityToDto(newCity);
    }

    @Override
    @Transactional
    public void update(UpdateCityRequest updateCityRequest) {
        cityRepository.findById(updateCityRequest.getId()).ifPresent(city -> {
            cityRepository.updateDescription(city.getId(), updateCityRequest.getDescription());
        });
    }

    @Override
    public void delete(Long id) {
        cityRepository.deleteById(id);
    }

}
